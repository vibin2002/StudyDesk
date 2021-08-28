package com.killerinstinct.studydesk.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SignUpViewModel: ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _isUserCreated = MutableLiveData(false)
    val isUserCreated: LiveData<Boolean> = _isUserCreated

    fun signUpWithCustomAuth(email: String,password: String){
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener {
                    _isUserCreated.value = true
                }.addOnFailureListener {
                    _isUserCreated.value = false
                }
        }
    }

}