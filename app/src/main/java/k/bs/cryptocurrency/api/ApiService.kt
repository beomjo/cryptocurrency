package k.bs.cryptocurrency.api

import k.bs.cryptocurrency.api.service.ApiCoin

object ApiService {
    private const val baseUrl = "https://api.coinranking.com"

    fun cryptoCurrency() = RetrofitAdapter.getInstance(baseUrl)
        .create(ApiCoin::class.java)
}