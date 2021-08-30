package com.killerinstinct.studydesk.ui.tutor.drawer.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.FragmentAddAssignmentBinding

class AddAssignmentFragment : Fragment(R.layout.fragment_add_assignment) {

    private lateinit var binding: FragmentAddAssignmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddAssignmentBinding.bind(view)
    }

}