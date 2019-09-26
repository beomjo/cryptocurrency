package k.bs.cryptocurrency.paging.base

import androidx.paging.PositionalDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePositionalDataSource<T> : PositionalDataSource<T>() {
    lateinit var onDataSourceLoading: OnDataSourceLoading
    protected var compositeDisposable = CompositeDisposable()

    abstract var startPosition: Int

    protected abstract fun loadInitialData(
        params: LoadInitialParams,
        callback: LoadInitialCallback<T>
    )

    protected abstract fun loadAdditionalData(
        params: LoadRangeParams,
        callback: LoadRangeCallback<T>
    )

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<T>) {
        onDataSourceLoading.onFirstFetch()
        loadInitialData(params, callback)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) {
        onDataSourceLoading.onPageLoading()
        loadAdditionalData(params, callback)

    }

    protected fun submitInitialData(
        items: List<T>,
        params: LoadInitialParams,
        callback: LoadInitialCallback<T>
    ) {
        if (items.isNotEmpty()) {
            onDataSourceLoading.onFirstFetchEndWithData()
            callback.onResult(items, startPosition, params.requestedLoadSize)
        } else {
            onDataSourceLoading.onFirstFetchEndWithoutData()
        }
    }

    protected fun submitData(
        items: List<T>,
        callback: LoadRangeCallback<T>
    ) {
        callback.onResult(items)
        onDataSourceLoading.onPageLoadingEnd()
    }

    protected fun isLastPage() {
        onDataSourceLoading.onPageLoadingEnd()
    }

    protected fun isQueryEmpty() {
        onDataSourceLoading.onFirstFetchEndWithoutData()
    }

    protected fun submitInitialError(error: Throwable) {
        onDataSourceLoading.onError()
        error.printStackTrace()
    }


    protected fun submitError(error: Throwable) {
        onDataSourceLoading.onError()
        error.printStackTrace()
    }

    override fun invalidate() {
        compositeDisposable.dispose()
        super.invalidate()
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

}