package com.example.staselovich_p4.ui.information

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.staselovich_p4.dataBase.CoinEntity
import com.example.staselovich_p4.repository.CoinDatabaseRepository
import com.example.staselovich_p4.repository.CoinRepository
import kotlinx.coroutines.launch


class InformationViewModel @ViewModelInject constructor(private val repository: CoinRepository,
private val dbrepository : CoinDatabaseRepository) :
    ViewModel() {


    fun insert(entity:CoinEntity) {
            viewModelScope.launch {
                dbrepository.insert(entity)
            }
    }

    private var coins = MutableLiveData("")
    var allCoins = coins.switchMap { repository.getAllCoins(it).cachedIn(viewModelScope) }

    fun refresh() {
        coins.value = "${coins.value}"
    }

    fun searchCoins(query: String) {
        viewModelScope.launch {
            coins.value = query
        }
    }


}
