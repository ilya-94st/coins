package com.example.staselovich_p4.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.staselovich_p4.model.CoinModel


@Entity(tableName = "coin_entity")
data class CoinEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val symbol: String
)