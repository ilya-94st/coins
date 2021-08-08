package com.example.staselovich_p4.ui.information

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.staselovich_p4.R
import com.example.staselovich_p4.adapter.CoinLoadAdapter
import com.example.staselovich_p4.dataBase.CoinEntity
import com.example.staselovich_p4.databinding.FragmentInformationBinding
import com.example.staselovich_p4.model.CoinModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class InformationFragment : Fragment(R.layout.fragment_information), CoinAdapter.OnItemClick{
    private var _binding: FragmentInformationBinding? = null
    private val binding get() = _binding!!
    var timer: Timer? =null
    private val viewModel by viewModels<InformationViewModel>()
    private lateinit var adapter: CoinAdapter
    lateinit var shared: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentInformationBinding.bind(view)
        binding.recyclerCoin.setHasFixedSize(true)
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility = View.VISIBLE
        initAdapter()
        exit()
        searchVisibility()
        swipeRefresh()
        shared = requireContext().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        binding.buttonRetry.setOnClickListener {
            adapter.retry()
        }
        binding.searchView.addTextChangedListener(searchTextWatcher)
    }

    private val searchTextWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(arg0: Editable) {
            if (!binding.searchView.text.isNullOrEmpty()) {
                timer = Timer()
                timer?.schedule(object : TimerTask() {
                    override fun run() {
                        Handler(Looper.getMainLooper()).postDelayed({
                            viewModel.searchCoins(binding.searchView.text.toString())
                            binding.searchView.hideKeyboard()
                        }, 0)
                    }
                }, 1500)
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
    private fun initAdapter() {
        adapter = CoinAdapter(this)
        binding.recyclerCoin.adapter = adapter
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
        adapter.addLoadStateListener { loadState->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerCoin.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error
                if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1) {
                    recyclerCoin.isVisible = false
                }
            }
        }
    }
    private fun swipeRefresh() {
        binding.swipe.setOnRefreshListener {
            viewModel.searchCoins("")
            adapter.refresh()
            binding.swipe.isRefreshing = false
        }
    }
    private fun searchVisibility() {
        binding.buttonSearch.setOnClickListener {
            binding.buttonSearch.visibility = View.INVISIBLE
            binding.searchView.visibility = View.VISIBLE
        }
    }
    private fun exit() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        binding.buttonExit.setOnClickListener {
            val editor : SharedPreferences.Editor? = shared.edit()
            editor?.clear()
            editor?.apply()
            FirebaseAuth.getInstance().signOut()
            val googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
            googleSignInClient.signOut()
            findNavController().navigateUp()
        }
    }
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
    override fun onDestroy() {
        super.onDestroy()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility = View.INVISIBLE
    }

    override fun onItemClick(coin: CoinModel) {
        coin.id?.let { CoinEntity(it, coin.name, coin.symbol) }?.let { viewModel.insert(it) }
    }
}
