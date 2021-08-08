package com.example.staselovich_p4.ui.registration

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class RegistrationViewModel @ViewModelInject constructor() : ViewModel() {
    private var userToken = String()

    fun getUserToken() : String {
        return userToken
    }

    fun createRequest(clientId: String): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(clientId)
            .requestEmail()
            .build()
    }

    fun setUserToken(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)!!
            userToken = account.idToken!!
        } catch (e: ApiException) {
            Log.d("Error", e.toString())
        }
    }

}
