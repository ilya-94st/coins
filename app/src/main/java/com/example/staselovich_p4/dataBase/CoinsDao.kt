package com.example.staselovich_p4.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CoinsDao {

    @Query("select * from coin_entity")
    fun readAll() : LiveData<List<CoinEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CoinEntity)

    @Query("DELETE FROM coin_entity")
    fun deleteAll()

}