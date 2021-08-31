package com.killerinstinct.studydesk.ui.student.drawer.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.FragmentStudentDashboardBinding

class StudentDashBoardFragment : Fragment(R.layout.fragment_student_dashboard) {

    lateinit var binding: FragmentStudentDashboardBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStudentDashboardBinding.bind(view)

        val viewPagerAdapter = StudentViewPagerAdapter(findNavController(),requireActivity())
        binding.stdViewpager.adapter = viewPagerAdapter
        TabLayoutMediator(
            binding.stdTablayout, binding.stdViewpager
        ) { tab, position ->
            when(position){
                0 -> tab.text = "Assignment"
                1 -> tab.text = "Test"
                2 -> tab.text = "Calendar"
            }
        }.attach()


    }
}