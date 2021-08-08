package com.example.staselovich_p4.ui.favorites

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.staselovich_p4.dataBase.CoinEntity
import com.example.staselovich_p4.repository.CoinDatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel @ViewModelInject constructor(private val dbrepository : CoinDatabaseRepository
) : ViewModel() {
    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            dbrepository.deleteAll()
        }
    }
    val readAll: LiveData<List<CoinEntity>> = dbrepository.readAll
}
