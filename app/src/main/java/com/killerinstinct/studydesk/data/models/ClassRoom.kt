package com.killerinstinct.studydesk.data.models

data class ClassRoom(
    val code: String = "",
    val className: String = "",
    val subject: String = "",
    val tutor: String = "",
    val students: List<Student> = listOf(),
    val assignments: List<Assignment> = listOf(),
    val tests: List<Test> = listOf()
)