package k.bs.cryptocurrency.scene.detail

import android.os.Bundle
import androidx.annotation.LayoutRes
import k.bs.cryptocurrency.R
import k.bs.cryptocurrency.base.BaseActionBarActivity
import k.bs.cryptocurrency.databinding.ActivityCoinDetailBinding
import k.bs.cryptocurrency.model.ModelCoin

class CoinDetailActivity : BaseActionBarActivity<ActivityCoinDetailBinding, CoinDetailVm>() {
    @LayoutRes
    override val resId: Int = R.layout.activity_coin_detail
    override val viewModel: CoinDetailVm = CoinDetailVm()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arguments = getInput<ModelCoin.Coin>()
        setActionBarTitleAndBackBtn(arguments.name)

        viewModel.init(arguments)
    }

    override fun onBackPressed() {
        finishWithResponse(viewModel.isFavorite.get())
    }
}
