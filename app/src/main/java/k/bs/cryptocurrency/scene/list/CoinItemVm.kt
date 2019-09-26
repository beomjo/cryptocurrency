package k.bs.cryptocurrency.scene.list

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import k.bs.cryptocurrency.common.util.round
import k.bs.cryptocurrency.common.util.rxresult.putObject
import k.bs.cryptocurrency.common.util.rxresult.startActivityForResult
import k.bs.cryptocurrency.common.util.rxresult.toIntent
import k.bs.cryptocurrency.model.ModelCoin
import k.bs.cryptocurrency.scene.detail.CoinDetailActivity

class CoinItemVm(private val model: ModelCoin.Coin) {
    val iconUrl get() = model.iconUrl
    val name get() = model.name
    val symbol get() = model.symbol
    val price get() = model.price.round(2)

    val isFavorite = ObservableBoolean(false)

    @SuppressLint("CheckResult")
    fun moveDetail(v: View) {
        CoinDetailActivity::class.java.toIntent()
            .putObject(model)
            .startActivityForResult<Boolean>(v.context)
            .subscribe({ select ->
                isFavorite.set(select)
                model.isCheck = select
            }, {
                Log.e(this::class.java.canonicalName, it.message)
            })
    }
}