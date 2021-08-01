package com.example.staselovich_p4.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoinModel(
    val id: Int,
    val name: String,
    val symbol: String,
    val quote: Quote,
) : Parcelable {

    @Parcelize
    data class Quote(
        val USD : CoinToUsd
    ) : Parcelable {

        @Parcelize
        data class CoinToUsd(
            val price: Double,
            val percent_change_1h : Double,
            val percent_change_24h: Double,
            val percent_change_7d: Double,
        ) : Parcelable
    }
}




