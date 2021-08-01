package com.example.staselovich_p4.ui.registration

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.staselovich_p4.R
import com.example.staselovich_p4.base.BaseFragment
import com.example.staselovich_p4.databinding.FragmentRegistrationBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*

const val RC_SIGN_IN = 0
class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>(){
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var auth: FirebaseAuth
    private val viewModel : RegistrationViewModel by viewModels()
    lateinit var shared: SharedPreferences
    var isremembered: Boolean = false


    override fun getBinding() = R.layout.fragment_registration
    @DelicateCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        mGoogleSignInClient = viewModel.createRequest(getString(R.string.default_web_client_id), requireActivity())
        animation()
        shared = requireContext().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        binding.signInButton.setOnClickListener {
            signIn()
        }
        binding.buttonLogin.setOnClickListener {
            val checked: Boolean = binding.checkBox.isChecked
            val editor: SharedPreferences.Editor = shared.edit()
            editor.putBoolean("Boo", checked)
            editor.apply()
            Navigation.findNavController(requireView())
                .navigate(R.id.action_registrationFragment_to_informationFragment)
        }
    }
    @DelicateCoroutinesApi
    private fun animation() {
        val fallinAnimation = AnimationUtils.loadAnimation(context, R.anim.fall)
        val fallinAnimationButton = AnimationUtils.loadAnimation(context, R.anim.animation_left)
        val fallinAnimationButtonUp = AnimationUtils.loadAnimation(context, R.anim.animation_up)
        val slow = AnimationUtils.loadAnimation(context, R.anim.slow_anim)
        binding.imageView.startAnimation(fallinAnimation)
        binding.signInButton.startAnimation(fallinAnimationButton)
        binding.buttonLogin.startAnimation(fallinAnimationButtonUp)
        binding.checkBox.startAnimation(slow)
        binding.checkBox.visibility = View.VISIBLE
    }
    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent;
        startActivityForResult(signInIntent,RC_SIGN_IN)
    }
    override fun onStart() {
        super.onStart()
        isremembered = shared.getBoolean("Boo", false)
        if (isremembered) {
            findNavController().navigate(R.id.action_registrationFragment_to_informationFragment)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                viewModel.firebaseAuthWithGoogle(account.idToken!!, auth)
            } catch (e: ApiException) {
                println("/////////////////$e")
            }
        }
    }
}