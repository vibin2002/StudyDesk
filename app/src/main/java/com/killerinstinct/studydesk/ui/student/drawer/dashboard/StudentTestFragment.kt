package com.killerinstinct.studydesk.ui.student.drawer.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.killerinstinct.studydesk.databinding.FragmentStudentTestBinding

class StudentTestFragment : Fragment() {

    lateinit var binding: FragmentStudentTestBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStudentTestBinding.bind(view)


    }

}