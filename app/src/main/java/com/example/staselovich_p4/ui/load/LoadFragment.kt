package com.example.staselovich_p4.ui.load

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.example.staselovich_p4.R
import com.example.staselovich_p4.base.BaseFragment
import com.example.staselovich_p4.databinding.FragmentLoadBinding
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
            activity?.runOnUiThread {
               Navigation.findNavController(requireView())
                   .navigate(R.id.action_loadFragment_to_registrationFragment)

            }
        }
    }
}
