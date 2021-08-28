package com.killerinstinct.studydesk.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginViewModel: ViewModel()  {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()


    fun loginUser(email: String,password: String, makeToast: (String) -> Unit){
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // TODO( Check student or tutor )
                makeToast("Student")
            }.addOnFailureListener {
                makeToast("Failure")
            }
    }

}