package com.killerinstinct.studydesk.ui.tutor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.killerinstinct.studydesk.Utils
import com.killerinstinct.studydesk.data.models.Assignment
import com.killerinstinct.studydesk.data.models.ClassRoom
import com.killerinstinct.studydesk.data.models.Test
import com.killerinstinct.studydesk.data.models.Tutor

class TutorMainViewModel : ViewModel() {

    private val _tutor = MutableLiveData<Tutor>(null)
    val tutor: LiveData<Tutor> = _tutor

    private val _classRooms = MutableLiveData<List<ClassRoom>>(listOf())
    val classRooms : LiveData<List<ClassRoom>> = _classRooms

    private val db = FirebaseFirestore.getInstance()
    private val userUid = FirebaseAuth.getInstance().currentUser?.uid

    fun getTutorData(gotTutor: (Boolean) -> Unit) {
        val mutableList = mutableListOf<ClassRoom>()
        userUid?.let { uid ->
            db.collection("Tutors")
                .document(uid)
                .get().addOnSuccessListener {
                    _tutor.value = it.toObject(Tutor::class.java)
                    Log.d("getTutorData", _tutor.value.toString())
                    db.collection("Classrooms")
                        .get()
                        .addOnSuccessListener { documents ->
                            for(doc in documents){
                                val classroom = doc.toObject(ClassRoom::class.java)
                                if (classroom.code in _tutor.value!!.classRooms){
                                    mutableList.add(classroom)
                                }
                            }
                            _classRooms.value = mutableList.toList()
                            Log.d("ClassRoomDA", _classRooms.value.toString())
                        }
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
        tutor: String,
        isSuccessful: (Boolean) -> Unit
    ) {
        val classRoomUID = Utils.randomString()
        val classRoom = ClassRoom(
            classRoomUID,
            className,
            subject,
            tutor,
            listOf(),
            listOf(),
            listOf()
        )
        db.collection("Classrooms")
            .document(classRoomUID)
            .set(classRoom)
            .addOnSuccessListener {
                db.collection("Tutors")
                    .document(userUid!!)
                    .update("classRooms", FieldValue.arrayUnion(classRoomUID))
                    .addOnSuccessListener {
                        Log.d("addClassRoom:", "Success")
                        isSuccessful(true)
                    }.addOnFailureListener {
                        isSuccessful(false)
                    }
            }.addOnFailureListener {
                Log.d("addClassRoom:", it.message.toString())
                isSuccessful(false)
            }
        Log.d("addClassRoom:", "Failure 2")
    }

    fun addAssignment(
        title: String,
        description: String,
        classRoomId: String,
        date: String,
        time: String,
        isSuccessful: (Boolean) -> Unit
    ) {
        val assignmentId = Utils.randomString()
        val assignment = Assignment(
            title,
            description,
            classRoomId,
            date,
            time
        )
        db.collection("Assignments")
            .document(assignmentId)
            .set(assignment)
            .addOnSuccessListener {
                db.collection("Classrooms")
                    .document(classRoomId)
                    .update("assignments", FieldValue.arrayUnion(assignmentId))
                    .addOnSuccessListener {
                        Log.d("addAssignment", "Success")
                        isSuccessful(true)
                    }.addOnFailureListener {
                        isSuccessful(false)
                    }
            }.addOnFailureListener {
                Log.d("addAssignment:", it.message.toString())
                isSuccessful(false)
            }
    }

    fun addTest(
        title: String,
        description: String,
        subject: String,
        classRoomId: String,
        date: String,
        time: String,
        isSuccessful: (Boolean) -> Unit
    ) {
        val testId = Utils.randomString()
        val test = Test(
            title,
            description,
            subject,
            classRoomId,
            date,
            time
        )
        db.collection("Tests")
            .document(testId)
            .set(test)
            .addOnSuccessListener {
                db.collection("Classrooms")
                    .document(classRoomId)
                    .update("tests", FieldValue.arrayUnion(testId))
                    .addOnSuccessListener {
                        Log.d("addTest", "Success")
                        isSuccessful(true)
                    }.addOnFailureListener {
                        isSuccessful(false)
                    }
            }.addOnFailureListener {
                Log.d("addTest:", it.message.toString())
                isSuccessful(false)
            }
    }

}