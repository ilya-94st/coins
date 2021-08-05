package com.example.staselovich_p4.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.staselovich_p4.api.CoinApi
import com.example.staselovich_p4.api.CoinPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinRepository @Inject constructor(
    private val coinApi: CoinApi
) {

    fun getAllCoins(query:String) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CoinPagingSource(coinApi,query) }
        ).liveData
}

