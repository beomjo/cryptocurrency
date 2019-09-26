package k.bs.cryptocurrency.paging.coin

import android.util.Log
import androidx.paging.PositionalDataSource
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import k.bs.cryptocurrency.api.ApiService
import k.bs.cryptocurrency.common.SchedulerProvider
import k.bs.cryptocurrency.model.ModelCoin
import k.bs.cryptocurrency.paging.base.OnDataSourceLoading
import k.bs.cryptocurrency.scene.list.CoinItemVm

class CoinDataSource(private val schedulerProvider: SchedulerProvider) :
    PositionalDataSource<CoinItemVm>() {
    private val TAG = this::class.java.canonicalName

    lateinit var onDataSourceLoading: OnDataSourceLoading
    private var compositeDisposable = CompositeDisposable()

    private val startPosition = 0

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<CoinItemVm>
    ) {

        Log.d("bsjo","bsjo  params.pageSize ${params.pageSize}")
        Log.d("bsjo","bsjo  params.requestedLoadSize ${params.requestedLoadSize}")
        Log.d("bsjo","bsjo  params.requestedStartPosition ${params.requestedStartPosition}")

        onDataSourceLoading.onFirstFetch()

        loadCoins(offset = 0)
            .subscribe({ result ->
                if (result.data.coins.isNotEmpty()) {
                    onDataSourceLoading.onFirstFetchEndWithData()
                    callback.onResult(result.data.coins.map {
                        CoinItemVm(
                            it
                        )
                    }, startPosition, result.data.stats.total)
                } else
                    onDataSourceLoading.onFirstFetchEndWithoutData()
            }, { e ->
                submitError(e)
            })
            .let(this::addDisposable)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<CoinItemVm>) {

        Log.d("bsjo","bsjo  params.startPosition ${params.startPosition}")
        Log.d("bsjo","bsjo  params.loadSize ${params.loadSize}")
        onDataSourceLoading.onPageLoading()

        loadCoins(offset = params.startPosition)
            .subscribe({ result ->
                callback.onResult(result.data.coins.map {
                    CoinItemVm(
                        it
                    )
                })
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