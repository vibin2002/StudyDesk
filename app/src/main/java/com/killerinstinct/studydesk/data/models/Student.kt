package com.killerinstinct.studydesk.data.models

data class Student(
    val name: String = "",
    val classRooms: List<String> = listOf(),
    val assignments: List<String> = listOf(),
    val tests: List<String> = listOf()
)