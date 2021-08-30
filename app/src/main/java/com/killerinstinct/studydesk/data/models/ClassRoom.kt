package com.killerinstinct.studydesk.data.models

data class ClassRoom(
    val code: String = "",
    val className: String = "",
    val subject: String = "",
    val tutor: String = "",
    val students: List<String> = listOf(),
    val assignments: List<String> = listOf(),
    val tests: List<String> = listOf()
)