package k.bs.cryptocurrency.scene

import androidx.annotation.LayoutRes
import k.bs.cryptocurrency.R
import k.bs.cryptocurrency.base.BaseBindingActivity
import k.bs.cryptocurrency.databinding.ActivityCryptoCurrencyBinding

class CryptoCurrencyActivity :
    BaseBindingActivity<ActivityCryptoCurrencyBinding, CryptoCurrencyVm>() {
    @LayoutRes
    override val resId: Int = R.layout.activity_crypto_currency

    override val viewModel: CryptoCurrencyVm = CryptoCurrencyVm()
}
