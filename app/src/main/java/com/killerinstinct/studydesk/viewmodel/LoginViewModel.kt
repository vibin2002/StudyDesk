package com.killerinstinct.studydesk.viewmodel

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.killerinstinct.studydesk.data.models.LoginCheck
import com.killerinstinct.studydesk.ui.student.StudentMainActivity
import com.killerinstinct.studydesk.ui.tutor.TutorMainActivity

class LoginViewModel: ViewModel()  {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val mAuth = FirebaseAuth.getInstance()


    fun loginUser(email: String,password: String, makeToast: (String) -> Unit){
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                db.collection("LoginCheck")
                    .document("LoginCheck")
                    .get().addOnCompleteListener {
                        if (it.isSuccessful) {
                            val loginCheck = it.result.toObject(LoginCheck::class.java)
                            when {
                                loginCheck!!.students.contains(mAuth.currentUser?.uid) -> {
                                    Log.d("LoginChecker","Stuendt")
                                    makeToast("Student")
                                }
                                loginCheck.tutors.contains(mAuth.currentUser?.uid) -> {
                                    Log.d("LoginChecker","Tutor")
                                    makeToast("Tutor")
                                }
                                else -> {
                                    makeToast("Failure")
                                }
                            }
                        } else {
                            makeToast("Failure")
                        }
                    }
            }.addOnFailureListener {
                makeToast("Failure")
            }
    }

}