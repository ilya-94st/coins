package com.example.staselovich_p4.api

import com.example.staselovich_p4.model.CoinModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CoinApi {
    companion object {
        const val BASE_URL = "https://pro-api.coinmarketcap.com/v1/"
        const val API_KEY = "eb27ddb0-6845-4f51-8fce-c2781293709d"
    }

    @Headers("X-CMC_PRO_API_KEY: $API_KEY")
    @GET("cryptocurrency/listings/latest")
    suspend fun getAllCoins(
        @Query("limit") limit: Int = 100,
        @Query("start") start: Int = 1,
        @Query("convert") convert: String = "USD"
    ) : CoinResponse
}



