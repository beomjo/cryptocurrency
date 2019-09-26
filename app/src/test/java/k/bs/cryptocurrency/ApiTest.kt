package k.bs.cryptocurrency

import io.reactivex.schedulers.Schedulers
import k.bs.cryptocurrency.api.ApiService
import org.junit.Test

class ApiTest {
    @Test
    fun getCoins() {
        ApiService.cryptoCurrency()
            .coin(offset = 0)
            .subscribeOn(Schedulers.io())
            .test()
            .await()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValueAt(0) { it.data.stats.base == "USD" }
            .assertValueAt(0) { it.data.stats.limit == 10 }
            .assertValueAt(0) { it.data.stats.offset == 0 }
    }
}
