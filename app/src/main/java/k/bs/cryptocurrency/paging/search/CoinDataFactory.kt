package k.bs.cryptocurrency.paging.search

import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import k.bs.cryptocurrency.common.SchedulerProvider
import k.bs.cryptocurrency.koin.getKoinInstance
import k.bs.cryptocurrency.model.ModelCoin
import k.bs.cryptocurrency.paging.base.OnDataSourceLoading

class CoinDataFactory(
    private var loading: OnDataSourceLoading
) : DataSource.Factory<Int, ModelCoin>() {

    lateinit var source: CoinDataSource

    private val schedulerProvider = getKoinInstance<SchedulerProvider>()

    override fun create(): PositionalDataSource<ModelCoin> {
        source = CoinDataSource(schedulerProvider)
        source.onDataSourceLoading = loading
        return source
    }
}