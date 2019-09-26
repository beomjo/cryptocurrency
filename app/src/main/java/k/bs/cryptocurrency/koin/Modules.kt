package k.bs.cryptocurrency.koin

import k.bs.cryptocurrency.common.SchedulerProvider
import k.bs.cryptocurrency.scene.list.CoinListVm
import org.koin.dsl.module


val schdulers = module {
    factory { SchedulerProvider() }
}
val cryptoCurrency = module {
    factory { CoinListVm() }
}
