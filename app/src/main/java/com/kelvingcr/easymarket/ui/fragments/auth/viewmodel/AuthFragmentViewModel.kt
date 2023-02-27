package com.kelvingcr.easymarket.ui.fragments.auth.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.kelvingcr.easymarket.R
import com.kelvingcr.easymarket.repository.auth.AuthRepository
import com.kelvingcr.easymarket.utils.Constants.Companion.ON_SUCESS_LOGIN
import com.kelvingcr.easymarket.utils.Constants.Companion.ON_SUCESS_REGISTER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthFragmentViewModel  @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    val signUpLiveData = MutableLiveData<String>()
    val signInLiveData = MutableLiveData<String>()
    val currentUserLiveData = MutableLiveData<FirebaseUser?>()
    val disconnectUserLiveData = MutableLiveData<Boolean>()

     fun signUp(email: String, password: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = withContext(Dispatchers.Default) {
                repository.signUp(email, password)
            }
            response.addOnSuccessListener {
                signUpLiveData.value = ON_SUCESS_REGISTER
            }.addOnFailureListener {
                signUpLiveData.value = it.message
            }
        }
    }
    fun signIn(email: String, password: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = withContext(Dispatchers.Default) {
                repository.signIn(email, password)
            }
            response.addOnSuccessListener {
                signInLiveData.value = ON_SUCESS_LOGIN
            }.addOnFailureListener {
                signInLiveData.value = it.message
            }
        }
    }

    fun getCurrentUser() {
        CoroutineScope(Dispatchers.Main).launch {
            val response = withContext(Dispatchers.Default) {
                repository.getCurrentUser()
            }
                currentUserLiveData.value = response
        }
    }

    fun disconnectUser() {
        CoroutineScope(Dispatchers.Main).launch {
            val response = withContext(Dispatchers.Default) {
                repository.disconnectUser()
            }
            disconnectUserLiveData.value = response
        }
    }
}