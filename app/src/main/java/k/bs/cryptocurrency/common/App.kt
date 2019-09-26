package k.bs.cryptocurrency.common

import android.app.Application
import k.bs.cryptocurrency.koin.cryptoCurrency
import k.bs.cryptocurrency.koin.schdulers
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import rx_activity_result2.RxActivityResult

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        RxActivityResult.register(this)

        startKoin {
            androidContext(this@App)
            modules(cryptoCurrency, schdulers)
        }
    }
}