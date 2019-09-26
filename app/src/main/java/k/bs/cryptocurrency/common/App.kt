package k.bs.cryptocurrency.common

import android.app.Application
import rx_activity_result2.RxActivityResult

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        RxActivityResult.register(this)

    }
}