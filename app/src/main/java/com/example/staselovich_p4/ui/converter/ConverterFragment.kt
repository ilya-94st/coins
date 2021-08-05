package com.example.staselovich_p4.ui.converter

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.staselovich_p4.R
import com.example.staselovich_p4.databinding.FragmentConverterBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ConverterFragment : Fragment(R.layout.fragment_converter) {
    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<ConverterViewModel>()
    var timer: Timer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentConverterBinding.bind(view)
        binding.coins = viewModel
        examinationIsEmptyPrise()
        binding.coinEditext.addTextChangedListener(searchTextWatcher)
        binding.fragmentCovert.setOnClickListener {
            binding.coinEditext.hideKeyboard()
        }
        binding.bitcoinImage.setOnClickListener { toArraysCoinFragment(true) }
        binding.currensyImage.setOnClickListener { toArraysCoinFragment(false) }
    }

    private val searchTextWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(arg0: Editable) {
            if (!binding.coinEditext.text.isNullOrEmpty()) {
                timer = Timer()
                timer?.schedule(object : TimerTask() {
                    override fun run() {
                        Handler(Looper.getMainLooper()).postDelayed({
                            if (examinationIsEmpty()) {
                                binding.textConvert.text =
                                    viewModel.fromCurrency?.quote?.USD?.price?.let {
                                        viewModel.toCurrency?.quote?.USD?.price?.let { it1 ->
                                            viewModel.convert(
                                                it, binding.coinEditext.text.toString().toDouble(),
                                                it1
                                            )
                                        }
                                    }
                                binding.totalCost.text = binding.coinEditext.text.toString()
                                binding.textDollors.text =
                                    viewModel.fromCurrency?.quote?.USD?.price?.let { it1 ->
                                        viewModel.countDollors(
                                            it1, binding.coinEditext.text.toString().toDouble()
                                        )
                                    }
                                binding.allCounDollars.text =
                                    viewModel.fromCurrency?.quote?.USD?.price?.let { it1 ->
                                        viewModel.countDollors(
                                            it1, binding.coinEditext.text.toString().toDouble()
                                        )
                                    }
                                binding.coinEditext.hideKeyboard()
                            }
                        }, 0)
                    }
                }, 1000)
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (timer != null) {
                timer?.cancel()
            }
        }
    }

    fun toArraysCoinFragment(chooser: Boolean) {
        val action =
            ConverterFragmentDirections.actionConverterFragmentToArrayCoinsFragment(chooser)
        findNavController().navigate(action)
    }

    fun examinationIsEmptyPrise() {
        binding.textDollors.text =
            viewModel.fromCurrency?.quote?.USD?.price?.let { viewModel.fromCoin(it) }
        if (viewModel.fromCurrency?.quote?.USD?.price == null) {
            binding.textTotalCost.visibility = View.INVISIBLE
            binding.totalCost.visibility = View.INVISIBLE
        } else {
            binding.textTotalCost.visibility = View.VISIBLE
            binding.totalCost.visibility = View.VISIBLE
        }
    }

    fun examinationIsEmpty(): Boolean {
        if (binding.coinEditext.text.isNullOrEmpty()) {
            Toast.makeText(context, "введите число", Toast.LENGTH_SHORT).show()
            return false
        } else if (viewModel.toCurrency?.quote?.USD?.price == null) {
            Toast.makeText(context, "выбирите валюту 2", Toast.LENGTH_SHORT).show()
            return false
        } else if (viewModel.fromCurrency?.quote?.USD?.price == null) {
            Toast.makeText(context, "выбирите валюту 1", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}


