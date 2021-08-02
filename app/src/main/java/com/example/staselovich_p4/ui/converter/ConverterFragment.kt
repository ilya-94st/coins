package com.example.staselovich_p4.ui.converter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.staselovich_p4.R
import com.example.staselovich_p4.adapter.CoinAdapter
import com.example.staselovich_p4.adapter.CoinSpinnerAdapter
import com.example.staselovich_p4.base.BaseFragment
import com.example.staselovich_p4.databinding.FragmentConverterBinding
import com.example.staselovich_p4.databinding.FragmentInformationBinding

import com.example.staselovich_p4.ui.information.InformationViewModel
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

        binding.bitcoinImage.setOnClickListener {
            val action = ConverterFragmentDirections.actionConverterFragmentToArrayCoinsFragment(true)
            findNavController().navigate(action)
        }
        binding.currensyImage.setOnClickListener {
            val action = ConverterFragmentDirections.actionConverterFragmentToArrayCoinsFragment(false)
            findNavController().navigate(action)
        }
    }
}