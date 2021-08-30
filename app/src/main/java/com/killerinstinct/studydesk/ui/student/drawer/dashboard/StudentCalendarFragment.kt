package com.killerinstinct.studydesk.ui.student.drawer.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.FragmentStudentCalendarBinding

class StudentCalendarFragment : Fragment(R.layout.fragment_student_calendar) {

    lateinit var binding: FragmentStudentCalendarBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStudentCalendarBinding.bind(view)
    }

}