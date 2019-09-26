package k.bs.cryptocurrency.scene.list

import androidx.annotation.LayoutRes
import k.bs.cryptocurrency.R
import k.bs.cryptocurrency.base.BaseBindingActivity
import k.bs.cryptocurrency.databinding.ActivityCoinListBinding

class CoinListActivity :
    BaseBindingActivity<ActivityCoinListBinding, CoinListVm>() {

    @LayoutRes
    override val resId: Int = R.layout.activity_coin_list

    override val viewModel: CoinListVm =
        CoinListVm()
}
