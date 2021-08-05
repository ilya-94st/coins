package com.example.staselovich_p4.ui.array_coins

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.staselovich_p4.repository.CoinRepository

class ArrayCoinsViewModel @ViewModelInject constructor(private val repository: CoinRepository) :
    ViewModel() {
    private var coins = MutableLiveData("")
    val allCoins = coins.switchMap { repository.getAllCoins("").cachedIn(viewModelScope) }

}