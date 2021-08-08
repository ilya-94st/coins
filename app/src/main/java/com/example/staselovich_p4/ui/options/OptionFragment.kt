package com.example.staselovich_p4.ui.options

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.staselovich_p4.ui.main_activity.MainActivity
import com.example.staselovich_p4.R
import com.example.staselovich_p4.base.BaseFragment
import com.example.staselovich_p4.databinding.FragmentOptionBinding
import java.util.*


class OptionFragment : BaseFragment<FragmentOptionBinding>() {
    private lateinit var locale: Locale
    private var currentLanguage = ""
    private var currentLang: String? = null
    override fun getBinding() = R.layout.fragment_option
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            @SuppressLint("RestrictedApi")
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View?, selectedItemPosition: Int, selectedId: Long
            ) {
                if(selectedItemPosition == 0){
                    binding.button3.setOnClickListener {
                        setLocale("ru")
                    }
                }
                if (selectedItemPosition == 1){
                    binding.button3.setOnClickListener {
                        setLocale("en")
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    private fun setLocale(localeName: String) {
        if (localeName != currentLanguage) {
            locale = Locale(localeName)
            val res = resources
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.locale = locale
            res.updateConfiguration(conf, dm)
            val refresh = Intent(
                context,
                MainActivity::class.java
            )
            refresh.putExtra(currentLang, localeName)
            startActivity(refresh)
        }
    }

}