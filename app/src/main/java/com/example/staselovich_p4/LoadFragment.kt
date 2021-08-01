package com.example.staselovich_p4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.staselovich_p4.base.BaseFragment
import com.example.staselovich_p4.databinding.FragmentLoadBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadFragment: BaseFragment<FragmentLoadBinding>() {
    override fun getBinding() = R.layout.fragment_load
    @DelicateCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch {
            delay(1700)
            getActivity()?.runOnUiThread {
               Navigation.findNavController(requireView())
                   .navigate(R.id.action_loadFragment_to_registrationFragment)
            }
        }
    }
}
