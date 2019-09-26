package k.bs.cryptocurrency

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import k.bs.cryptocurrency.base.BaseSchedulerProvider
import k.bs.cryptocurrency.paging.coin.CoinDataSource
import org.junit.Test

class ApiTest {
    private val schedulers = object : BaseSchedulerProvider {
        override fun computation(): Scheduler {
            return Schedulers.computation()
        }

        override fun io(): Scheduler {
            return Schedulers.io()
        }

        override fun ui(): Scheduler {
            return Schedulers.trampoline()
        }
    }

    @Test
    fun getCoins() {
        //given
        val dataSource = CoinDataSource(schedulers)

        //when
        val testObserver = dataSource.loadCoins(0).test()

        //then
        testObserver
            .await()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValueAt(0) { it.data.stats.base == "USD" }
            .assertValueAt(0) { it.data.stats.limit == 10 }
            .assertValueAt(0) { it.data.stats.offset == 0 }
    }
}
