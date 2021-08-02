package com.example.staselovich_p4.ui.converter

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.ObservableDouble
import androidx.databinding.library.baseAdapters.BR
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.staselovich_p4.base.BaseVM
import com.example.staselovich_p4.model.CoinModel
import com.example.staselovich_p4.model.CoinRepository

class ConverterViewModel @ViewModelInject constructor(val repository: CoinRepository) :
    BaseVM() {

    init {
        Log.w("asd", "init")
    }

    val fromCount = ObservableDouble(0.0)
    val toCount = ObservableDouble(0.0)

    init {
        /*fromCount.addOnPropertyChangedCallback(object: Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {

            }
        })*/
    }

    @Bindable
    var fromCurrency: CoinModel? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.fromCurrency)
        }

    @Bindable
    var toCurrency: CoinModel? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.toCurrency)
        }
    }

