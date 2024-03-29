package k.bs.cryptocurrency.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import k.bs.cryptocurrency.BR

abstract class BaseBindingActivity<V : ViewDataBinding, VM : Any> : RxActivityBase() {
    private val disposables = CompositeDisposable()

    abstract val resId: Int

    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<V>(this, resId)
            .setVariable(BR.vm, viewModel)
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}