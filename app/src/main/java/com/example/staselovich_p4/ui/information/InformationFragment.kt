package com.example.staselovich_p4.ui.information

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.example.staselovich_p4.R
import com.example.staselovich_p4.adapter.CoinAdapter
import com.example.staselovich_p4.adapter.CoinLoadAdapter
import com.example.staselovich_p4.databinding.FragmentInformationBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InformationFragment : Fragment(R.layout.fragment_information){
    private var _binding: FragmentInformationBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<InformationViewModel>()
    private lateinit var adapter: CoinAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentInformationBinding.bind(view)
        binding.recyclerCoin.setHasFixedSize(true)
        adapter = CoinAdapter()
        binding.recyclerCoin.adapter = adapter
        binding.swipe.setOnRefreshListener {
            viewModel.refresh()
            binding.swipe.isRefreshing = false
        }
        binding.apply {
            recyclerCoin.setHasFixedSize(true)
            recyclerCoin.adapter = adapter.withLoadStateHeaderAndFooter(
                header = CoinLoadAdapter{adapter.retry()},
                footer = CoinLoadAdapter{adapter.retry()},
            )
        }
        viewModel.allCoins.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle,it)
        }
        binding.buttonRetry.setOnClickListener {
            adapter.retry()
        }
        adapter.addLoadStateListener { loadState->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerCoin.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error
                if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1) {
                    recyclerCoin.isVisible = false
                    textViewError.isVisible = true
                } else {
                    textViewError.isVisible = false
                }
            }
        }
    }
}
