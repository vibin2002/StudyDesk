package com.killerinstinct.studydesk.data.models

data class ClassRoom(
    val tutor: Tutor,
    val students: List<Student>
)