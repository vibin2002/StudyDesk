package com.killerinstinct.studydesk.ui.tutor.drawer.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.FragmentTutorTestBinding

class TutorTestFragment : Fragment(R.layout.fragment_tutor_test) {

    lateinit var binding: FragmentTutorTestBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTutorTestBinding.bind(view)

    }

}