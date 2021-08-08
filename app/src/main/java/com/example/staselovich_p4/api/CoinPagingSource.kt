package com.example.staselovich_p4.api

import androidx.paging.PagingSource
import com.example.staselovich_p4.model.CoinModel
import retrofit2.HttpException
import java.io.IOException

private const val COIN_STARTING_PAGE_INDEX = 1

class CoinPagingSource(
    private val coinApi: CoinApi, private val query: String
) : PagingSource<Int, CoinModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CoinModel> {
        val limit = 5000
        val start = params.key ?: COIN_STARTING_PAGE_INDEX


        return try {

            val response = coinApi.getAllCoins(limit, start)
            val result = response.data
                .filter {
                    it.name.lowercase().contains(query.lowercase())
                }

            LoadResult.Page(
                data = result,
                prevKey = null,
                nextKey = if (result.isEmpty()) null else start + 100,
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}

