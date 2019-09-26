package k.bs.cryptocurrency.scene.list

import android.util.Log
import k.bs.cryptocurrency.BR
import k.bs.cryptocurrency.R
import k.bs.cryptocurrency.paging.base.BasePaginationViewModel
import k.bs.cryptocurrency.paging.coin.CoinDataSourceFactory
import k.bs.cryptocurrency.scene.list.adapter.CoinAdapter
import k.bs.cryptocurrency.scene.list.adapter.CoinItemVm

class CoinListVm
    : BasePaginationViewModel<CoinDataSourceFactory, CoinItemVm>() {

    override var dataSourceFactory: CoinDataSourceFactory = CoinDataSourceFactory(getListener())

    override fun getPageSize(): Int = 10

    val adapter = CoinAdapter(R.layout.item_coin, BR.vm)

    init {
        submitItems()
        registerObserving()
    }

    private fun submitItems() {
        getItems()
            ?.subscribe(
                { items -> adapter.submitList(items) },
                { /** Error handle*/ }
            )
            .let { { addDisposable(it!!) } }
    }

    private fun registerObserving() {
        errorToastSubject.subscribe(
            { Log.e(this::class.java.canonicalName, "error betide") },
            { /** Error handle*/ })
            .let(this::addDisposable)

        clearDataSubject.subscribe(
            {
                clearDataSource()
                submitItems()
                adapter.notifyDataSetChanged()
            }, { /** Error handle*/ })
            .let(this::addDisposable)


        recyclerViewPageLoadingSubject
            .subscribe(
                { show -> show?.let { adapter.loading = show.peek() } },
                {}
            )
            .let(this::addDisposable)

        dataLoadingSubject
            .subscribe(
                { show -> },
                {}
            )
            .let(this::addDisposable)
    }
}