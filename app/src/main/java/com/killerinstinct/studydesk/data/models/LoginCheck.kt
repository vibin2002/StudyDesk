package com.killerinstinct.studydesk.data.models

data class LoginCheck(
    val students: List<String> = listOf(),
    val tutors: List<String> = listOf()
)
