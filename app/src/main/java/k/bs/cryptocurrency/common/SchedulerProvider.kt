package k.bs.cryptocurrency.common

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import k.bs.cryptocurrency.base.BaseSchedulerProvider

class SchedulerProvider : BaseSchedulerProvider {
    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}