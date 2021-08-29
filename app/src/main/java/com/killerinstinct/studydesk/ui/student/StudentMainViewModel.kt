package com.killerinstinct.studydesk.ui.student

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.killerinstinct.studydesk.data.models.Student
import com.killerinstinct.studydesk.data.models.Tutor

class StudentMainViewModel: ViewModel() {

    private val _student = MutableLiveData<Student>(null)
    val student: LiveData<Student> = _student

    fun addStudentToClass(classCode: String){

    }


}