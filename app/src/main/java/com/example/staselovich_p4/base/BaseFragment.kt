package com.example.staselovich_p4.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.staselovich_p4.tools.SnackBar


abstract class BaseFragment<B: ViewDataBinding> : Fragment() {
    protected lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getBinding(),container, false)
        return binding.root
    }
    fun snackBar(text: String) {
        SnackBar(binding.root ,text)
    }
    abstract fun getBinding(): Int

}