package com.killerinstinct.studydesk.ui.tutor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.killerinstinct.studydesk.data.models.ClassRoom
import com.killerinstinct.studydesk.data.models.Tutor

class TutorMainViewModel : ViewModel() {

    private val _tutor = MutableLiveData<Tutor>(null)
    val tutor: LiveData<Tutor> = _tutor

    private val db = FirebaseFirestore.getInstance()
    private val userUid = FirebaseAuth.getInstance().currentUser?.uid

    fun getTutorData(gotTutor: (Boolean) -> Unit) {
        userUid?.let { uid ->
            db.collection("Tutors")
                .document(uid)
                .get().addOnSuccessListener {
                    _tutor.value = it.toObject(Tutor::class.java)
                    Log.d( "getTutorData",_tutor.value.toString())
                    gotTutor(true)
                }
                .addOnFailureListener {
                    gotTutor(false)
                }
        }
    }

    fun addClassRoom(
        className: String,
        subject: String,
        tutor: Tutor,
        isSuccessful: (Boolean) -> Unit
    ) {
        val classRoom = ClassRoom(
            className,
            subject,
            tutor,
            listOf(),
            listOf(),
            listOf()
        )
        db.collection("Classroom")
            .add(classRoom)
            .addOnSuccessListener {
                Log.d("addClassRoom:","Success")
                isSuccessful(true)
            }.addOnFailureListener {
                Log.d("addClassRoom:","Failure 1")
                isSuccessful(false)
            }
        Log.d("addClassRoom:","Failure 2")
    }

}