package com.example.staselovich_p4.common

import android.content.Context
import androidx.room.Room
import com.example.staselovich_p4.api.CoinApi
import com.example.staselovich_p4.dataBase.CoinDatabase
import com.example.staselovich_p4.dataBase.CoinEntity
import com.example.staselovich_p4.dataBase.CoinsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Common {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(CoinApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideCoinApi(retrofit: Retrofit): CoinApi =
        retrofit.create(CoinApi::class.java)

    @Singleton
    @Provides
    fun provideSearchDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            CoinDatabase::class.java,
            "coin_entity"
        ).build()

    @Provides
    fun provideSearchDAO(coinDatabase: CoinDatabase): CoinsDao {
        return coinDatabase.coinDao()
    }
}

