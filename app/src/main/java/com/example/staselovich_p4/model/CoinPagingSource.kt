package com.example.staselovich_p4.model

import android.util.Log
import androidx.paging.PagingSource
import com.example.staselovich_p4.api.CoinApi
import retrofit2.HttpException
import java.io.IOException

private const val COIN_STARTING_PAGE_INDEX = 1
class CoinPagingSource(
    private val coinApi: CoinApi,
) : PagingSource<Int, CoinModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CoinModel> {
        val position = params.key ?: COIN_STARTING_PAGE_INDEX

        return try {
            val response = coinApi.getAllCoins()
            val result = response.data

            LoadResult.Page(
                data = result,
                prevKey = if (position == COIN_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (result.isEmpty()) null else position + 1,
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}

