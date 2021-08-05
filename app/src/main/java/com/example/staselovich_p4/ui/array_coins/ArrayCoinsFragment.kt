package com.example.staselovich_p4.ui.array_coins

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.staselovich_p4.R
import com.example.staselovich_p4.adapter.CoinSpinnerAdapter
import com.example.staselovich_p4.databinding.FragmentArrayCoinsBinding
import com.example.staselovich_p4.model.CoinModel
import com.example.staselovich_p4.ui.converter.ConverterViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ArrayCoinsFragment : Fragment(R.layout.fragment_array_coins), CoinSpinnerAdapter.OnItemClickListener {
    private var _binding: FragmentArrayCoinsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ArrayCoinsViewModel>()
    private val shared by activityViewModels<ConverterViewModel>()
    private lateinit var adapter: CoinSpinnerAdapter
    private val args by navArgs<ArrayCoinsFragmentArgs>()

    private var first: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentArrayCoinsBinding.bind(view)
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility = View.INVISIBLE
        binding.recyclerCoin.setHasFixedSize(true)
        adapter = CoinSpinnerAdapter(this)
        binding.recyclerCoin.adapter = adapter
        viewModel.allCoins.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle,it)
        }

        first = args.first
    }

    override fun onItemClick(coin: CoinModel) {
        if(args.first) {
            shared.fromCurrency = coin
        } else {
            shared.toCurrency = coin
        }
        val action = ArrayCoinsFragmentDirections.actionArrayCoinsFragmentToConverterFragment()
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility = View.VISIBLE
    }
}