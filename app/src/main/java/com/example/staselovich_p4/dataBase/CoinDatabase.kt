package com.example.staselovich_p4.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CoinEntity::class), version = 1, exportSchema = false)
abstract class CoinDatabase: RoomDatabase() {
    abstract fun coinDao(): CoinsDao
}
