package k.bs.cryptocurrency.paging.search

import android.util.Log
import androidx.paging.PositionalDataSource
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import k.bs.cryptocurrency.api.ApiService
import k.bs.cryptocurrency.common.SchedulerProvider
import k.bs.cryptocurrency.model.ModelCoin
import k.bs.cryptocurrency.paging.base.OnDataSourceLoading

class CoinDataSource(private val schedulerProvider: SchedulerProvider) :
    PositionalDataSource<ModelCoin.Coin>() {
    private val TAG = this::class.java.canonicalName

    lateinit var onDataSourceLoading: OnDataSourceLoading
    protected var compositeDisposable = CompositeDisposable()

    private val startPosition = 0

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<ModelCoin.Coin>
    ) {
        onDataSourceLoading.onFirstFetch()

        loadCoins(offset = 0)
            .subscribe({ result ->
                if (result.data.coins.isNotEmpty()) {
                    onDataSourceLoading.onFirstFetchEndWithoutData()
                    callback.onResult(result.data.coins, startPosition, result.data.stats.total)
                } else
                    onDataSourceLoading.onFirstFetchEndWithoutData()
            }, { e ->
                submitError(e)
            })
            .let(this::addDisposable)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<ModelCoin.Coin>) {
        loadCoins(offset = params.loadSize)
            .subscribe({ result ->
                callback.onResult(result.data.coins)
                onDataSourceLoading.onPageLoadingEnd()
            }, { e ->
                submitError(e)
            })
            .let(this::addDisposable)
    }


    private fun submitError(error: Throwable) {
        onDataSourceLoading.onError()
        error.printStackTrace()
        Log.e(TAG, error.message)
    }

    override fun invalidate() {
        compositeDisposable.dispose()
        super.invalidate()
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun loadCoins(offset: Int): Single<ModelCoin> {
        return ApiService.cryptoCurrency().coin(offset = offset)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }
}