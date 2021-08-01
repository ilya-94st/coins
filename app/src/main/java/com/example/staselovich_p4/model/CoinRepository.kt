package com.example.staselovich_p4.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.staselovich_p4.api.CoinApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinRepository @Inject constructor(
    private val coinApi: CoinApi
) {
    fun getAllCoins() =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CoinPagingSource(coinApi) }
        ).liveData
}

