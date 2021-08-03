package com.example.staselovich_p4.ui.converter

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.staselovich_p4.R
import com.example.staselovich_p4.databinding.FragmentConverterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConverterFragment : Fragment(R.layout.fragment_converter){
    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<ConverterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentConverterBinding.bind(view)
         binding.coins = viewModel
        binding.buttonconvert.setOnClickListener {
if ( examinationIsEmpty()){
    binding.textConvert.text =   viewModel.fromCurrency?.quote?.USD?.price?.let { viewModel.toCurrency?.quote?.USD?.price?.let { it1 ->
        viewModel.convert(it, binding.coinEditext.text.toString().toDouble(),
            it1)
    } }
    binding.textDollors.text = viewModel.fromCurrency?.quote?.USD?.price?.let { it1 ->
        viewModel.countDollors(
            it1, binding.coinEditext.text.toString().toDouble())
    }
    binding.allCounDollars.text = viewModel.fromCurrency?.quote?.USD?.price?.let { it1 ->
        viewModel.countDollors(
            it1, binding.coinEditext.text.toString().toDouble())
    }
}
            binding.coinEditext.hideKeyboard()
        }
        binding.fragmentCovert.setOnClickListener {
            binding.coinEditext.hideKeyboard()
        }
        binding.bitcoinImage.setOnClickListener {
            val action = ConverterFragmentDirections.actionConverterFragmentToArrayCoinsFragment(true)
            findNavController().navigate(action)
        }
        binding.currensyImage.setOnClickListener {
            val action = ConverterFragmentDirections.actionConverterFragmentToArrayCoinsFragment(false)
            findNavController().navigate(action)
        }
    }
    fun examinationIsEmpty(): Boolean {
        if(binding.coinEditext.text.isNullOrEmpty()){
            Toast.makeText(context,"введите число", Toast.LENGTH_SHORT).show()
            return false
        } else if(viewModel.toCurrency?.quote?.USD?.price == null){
            Toast.makeText(context,"выбирите валюту 2", Toast.LENGTH_SHORT).show()
            return false
        } else if(viewModel.fromCurrency?.quote?.USD?.price == null){
            Toast.makeText(context,"выбирите валюту 1", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
            }
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}


