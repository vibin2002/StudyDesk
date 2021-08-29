package com.killerinstinct.studydesk.data.models

data class ClassRoom(
    val code: String,
    val className: String,
    val subject: String,
    val tutor: String,
    val students: List<Student>,
    val assignments: List<Assignment>,
    val tests: List<Test>
)