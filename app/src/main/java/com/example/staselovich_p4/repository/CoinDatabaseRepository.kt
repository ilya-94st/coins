package com.example.staselovich_p4.repository

import androidx.lifecycle.LiveData
import com.example.staselovich_p4.dataBase.CoinEntity
import com.example.staselovich_p4.dataBase.CoinsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CoinDatabaseRepository @Inject constructor(private val coinDao:CoinsDao) {

    suspend fun insert(entity: CoinEntity){
       withContext(Dispatchers.IO){
           coinDao.insert(entity)
       }
    }
    val readAll: LiveData<List<CoinEntity>> = coinDao.readAll()

    fun deleteAll(){
        coinDao.deleteAll()
    }
}