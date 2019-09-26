package k.bs.cryptocurrency.scene.web

import android.os.Bundle
import k.bs.cryptocurrency.R
import k.bs.cryptocurrency.base.BaseBindingActivity
import k.bs.cryptocurrency.databinding.ActivityWebBinding

class WebActivity : BaseBindingActivity<ActivityWebBinding, WebVm>() {
    override val resId: Int = R.layout.activity_web
    override val viewModel: WebVm = WebVm()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = getInput<String>()
        viewModel.init(url)
    }
}
