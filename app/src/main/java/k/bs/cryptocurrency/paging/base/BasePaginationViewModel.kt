package k.bs.cryptocurrency.paging.base

import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

abstract class BasePaginationViewModel<T : DataSource.Factory<Boolean, K>, K> {
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    protected abstract var dataSourceFactory: T
    private var pagedObservable: Observable<PagedList<K>>? = null

    val clearDataSubject = PublishSubject.create<Event<Unit>>()
    val recyclerViewPageLoadingSubject = PublishSubject.create<Event<Boolean>>()
    val dataLoadingSubject = PublishSubject.create<Event<Boolean>>()
    val errorToastSubject = PublishSubject.create<Event<Unit>>()

    abstract fun getPageSize(): Int

    fun clearData() {
        this.clearDataSubject.onNext(Event(Unit))
    }

    fun clearDataSource() {
        dataSourceFactory.create()
        createPagedObservable()
    }

    fun getItems(): Observable<PagedList<K>>? {
        if (pagedObservable == null) {
            createPagedObservable()
        }
        return pagedObservable
    }

    private fun createPagedObservable() {
        pagedObservable = RxPagedListBuilder(
            dataSourceFactory,
            PagedList.Config.Builder()
                .setPageSize(getPageSize())
                .setInitialLoadSizeHint(getPageSize() * 3) // default setInitialLoadSizeHint = pageSize*3
                .setEnablePlaceholders(false)
                .build()
        )
            .buildObservable()
    }

    protected fun getListener(): OnDataSourceLoading {
        return object : OnDataSourceLoading {
            override fun onFirstFetch() {
                showDataLoadingProgress()
            }

            override fun onFirstFetchEndWithData() {
                showDataLoadingProgress()
            }

            override fun onFirstFetchEndWithoutData() {
                hideDataLoadingProgress()
            }

            override fun onPageLoading() {
                showPageLoadingBar()
            }

            override fun onPageLoadingEnd() {
                hidePageLoadingBar()
            }

            override fun onError() {
//                hidePageLoadingBar()
//                hideDataLoadingProgress()
                showErrorToast()
            }
        }
    }

    fun showPageLoadingBar() {
        recyclerViewPageLoadingSubject.onNext(Event(true))
    }

    fun hidePageLoadingBar() {
        recyclerViewPageLoadingSubject.onNext(Event(false))
    }

    fun showDataLoadingProgress() {
        dataLoadingSubject.onNext(Event(true))
    }

    fun hideDataLoadingProgress() {
        dataLoadingSubject.onNext(Event(false))
    }

    fun showErrorToast() {
        errorToastSubject.onNext(Event(Unit))
    }

    fun addDisposable(d: Disposable) = compositeDisposable.add(d)

    fun cleared() {
        compositeDisposable.clear()
    }
}