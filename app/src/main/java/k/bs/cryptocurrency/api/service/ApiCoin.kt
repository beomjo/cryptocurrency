package k.bs.cryptocurrency.api.service

import io.reactivex.Single
import k.bs.cryptocurrency.model.ModelCoin
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCoin {
    @GET("/v1/public/coins")
    fun coin(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int
    ): Single<ModelCoin>
}