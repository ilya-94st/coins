package com.example.staselovich_p4.ui.converter

import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.hilt.lifecycle.ViewModelInject
import com.example.staselovich_p4.base.BaseVM
import com.example.staselovich_p4.model.CoinModel
import com.example.staselovich_p4.repository.CoinRepository
import kotlin.math.roundToInt

class ConverterViewModel @ViewModelInject constructor(
    val repository: CoinRepository
) :
    BaseVM() {

    fun fromCoin(prise: Double): String {

        return ((prise * 100.0).roundToInt() / 100.0).toString()
    }

    fun convert(price1: Double, coint1: Double, price2: Double): String {
        val result = (price1 * coint1) / (price2 * 1)
        return ((result * 100.0).roundToInt() / 100.0).toString()
    }

    fun countDollors(prise: Double, count: Double): String {
        val result = prise * count
        return ((result * 100.0).roundToInt() / 100.0).toString()
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

