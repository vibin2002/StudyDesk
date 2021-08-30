package com.killerinstinct.studydesk.ui.tutor.drawer.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.FragmentTutorAssignmentBinding

class TutorAssignmentFragment : Fragment(R.layout.fragment_tutor_assignment) {

    lateinit var binding: FragmentTutorAssignmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTutorAssignmentBinding.bind(view)

    }


}