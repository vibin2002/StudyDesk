package com.killerinstinct.studydesk.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.killerinstinct.studydesk.data.models.Student
import com.killerinstinct.studydesk.data.models.Tutor
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun signUpWithCustomAuth(
        name: String,
        email: String,
        password: String,
        designation: String,
        makeToast: (String) -> Unit
    ) {
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    //  if user created
                    if (designation == "Student"){
                        val student = Student(
                            name, listOf(), listOf(), listOf()
                        )
                        auth.currentUser?.let { it1 ->
                            db.collection("Students")
                                .document(it1.uid)
                                .set(student)
                                .addOnSuccessListener {
                                    makeToast("SignUp successful")
                                }.addOnFailureListener {
                                    makeToast("SignUp failure")
                                }
                        }
                    }else if(designation == "Tutor"){
                        val tutor = Tutor(
                            name, listOf(), listOf(), listOf()
                        )
                        auth.currentUser?.let { it1 ->
                            db.collection("Tutors")
                                .document(it1.uid)
                                .set(tutor)
                                .addOnSuccessListener {
                                    makeToast("SignUp successful")
                                }.addOnFailureListener {
                                    makeToast("SignUp failure")
                                }
                        }
                    }
//                    makeToast(true)
                }.addOnFailureListener {
                    // if user not created
                    makeToast("User not created")
                }
        }
    }

}