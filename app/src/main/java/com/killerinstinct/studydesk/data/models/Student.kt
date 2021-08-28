package com.killerinstinct.studydesk.data.models

data class Student(
    val name: String,
    val classRooms: List<String>,
    val assignments: List<String>,
    val tests: List<String>
)