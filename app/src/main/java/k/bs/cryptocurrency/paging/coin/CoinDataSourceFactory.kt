package k.bs.cryptocurrency.paging.coin

import androidx.paging.DataSource
import k.bs.cryptocurrency.common.SchedulerProvider
import k.bs.cryptocurrency.koin.getKoinInstance
import k.bs.cryptocurrency.paging.base.OnDataSourceLoading
import k.bs.cryptocurrency.scene.list.CoinItemVm

class CoinDataSourceFactory(private var loading: OnDataSourceLoading) :
    DataSource.Factory<Int, CoinItemVm>() {

    lateinit var source: CoinDataSource

    private val schedulerProvider = getKoinInstance<SchedulerProvider>()

    override fun create(): DataSource<Int, CoinItemVm> {
        source = CoinDataSource(schedulerProvider)
        source.onDataSourceLoading = loading
        return source
    }
}