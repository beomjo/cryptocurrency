package k.bs.cryptocurrency.scene.detail

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import k.bs.cryptocurrency.model.ModelCoin
import java.lang.StringBuilder

class CoinDetailVm {
    val iconUrl = ObservableField<String>()
    val description = ObservableField<String>()
    val volume = ObservableField<String>()
    val price = ObservableField<String>()
    val history = ObservableField<String>()
    val change = ObservableField<String>()
    val isFavorite = ObservableBoolean(false)

    fun init(arguments: ModelCoin.Coin) {
        iconUrl.set(arguments.iconUrl)
        description.set(arguments.description)
        volume.set(arguments.volume.toString())
        price.set(arguments.price)
        history.set(arguments.history.take(7).let {
            StringBuilder().apply {
                appendln()
                it.forEachIndexed { index, s ->
                    appendln("$index : $s")
                }
            }
        }.toString())
        change.set(arguments.change.toString())
        isFavorite.set(arguments.isCheck)
    }

    fun check() {
        isFavorite.set(!isFavorite.get())
    }
}