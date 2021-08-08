package com.example.staselovich_p4.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.staselovich_p4.R
import com.example.staselovich_p4.databinding.FragmentFavoriteBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FavoriteViewModel>()
    private lateinit var adapter: FavoriteAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoriteBinding.bind(view)
        binding.recyclerCoin.setHasFixedSize(true)
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility = View.VISIBLE
        adapter = FavoriteAdapter()
        binding.recyclerCoin.adapter = adapter
        viewModel.readAll.observe(viewLifecycleOwner,{
            adapter.submitList(it)
        })
        binding.buttonDelete.setOnClickListener {
            viewModel.deleteAll()
        }
    }
}