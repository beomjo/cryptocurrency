package k.bs.cryptocurrency.scene.list.adapter

import k.bs.cryptocurrency.model.ModelCoin

class CoinItemVm(val model: ModelCoin.Coin) {
    val iconUrl get() = model.iconUrl
    val name get() = model.name
    val symbol get() = model.symbol
    val price get() = model.price
}