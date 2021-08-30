package com.killerinstinct.studydesk.ui.student

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.killerinstinct.studydesk.data.models.*

class StudentMainViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val userUid = FirebaseAuth.getInstance().currentUser?.uid

    private val _student = MutableLiveData<Student>(null)
    val student: LiveData<Student> = _student

    private val _classRooms = MutableLiveData<List<ClassRoom>>(listOf())
    val classRooms: LiveData<List<ClassRoom>> = _classRooms

    private val _assignments = MutableLiveData<List<Assignment>>(listOf())
    val assignments: LiveData<List<Assignment>> = _assignments

    private val _test = MutableLiveData<List<Test>>(listOf())
    val test: LiveData<List<Test>> = _test

    fun getStudentData(gotTutor: (Boolean) -> Unit) {
        val mutableList = mutableListOf<ClassRoom>()
        userUid?.let { uid ->
            db.collection("Students")
                .document(uid)
                .get().addOnSuccessListener {
                    _student.value = it.toObject(Student::class.java)
                    Log.d("getTutorData", _student.value.toString())
                    db.collection("Classrooms")
                        .get()
                        .addOnSuccessListener { documents ->
                            for (doc in documents) {
                                val classroom = doc.toObject(ClassRoom::class.java)
                                if (classroom.code in _student.value!!.classRooms) {
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

    fun getAllAssignments(gotAssignments: (Boolean)-> Unit){
        val mutableAssignmentList = mutableListOf<Assignment>()
        db.collection("Assignments")
            .get()
            .addOnSuccessListener { documents ->
                for (doc in documents){
                    val assignment = doc.toObject(Assignment::class.java)
                    if (assignment.classRoomCode in _student.value!!.classRooms){
                        mutableAssignmentList.add(assignment)
                    }
                }
                gotAssignments(true)
                _assignments.value = mutableAssignmentList.toList()
                Log.d("getAllAssignments",_assignments.value.toString())
            }.addOnFailureListener {
                gotAssignments(false)
            }
    }

    fun addStudentToClass(classCode: String, isAdded: (String) -> Unit) {
        db.collection("Classrooms")
            .document(classCode.trim())
            .update("students", FieldValue.arrayUnion(userUid))
            .addOnSuccessListener {
                db.collection("Students")
                    .document(userUid!!)
                    .update("classRooms",FieldValue.arrayUnion(classCode.trim()))
                    .addOnFailureListener {
                        isAdded("Failure")
                    }
                isAdded("Success")
            }.addOnFailureListener {
                isAdded("Failure")
            }

    }


}