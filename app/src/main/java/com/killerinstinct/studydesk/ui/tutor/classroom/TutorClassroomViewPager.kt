package com.killerinstinct.studydesk.ui.tutor.classroom

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.viewpager2.adapter.FragmentStateAdapter

class TutorClassroomViewPager(
    private val navController: NavController,
    private val fragmentActivity: FragmentActivity,
    private val code: String
) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                TutorClassroomAssignmentFragment(navController,code)
            }
            1 -> {
                TutorClassroomTestFragment(navController,code)
            }
            else -> {
                TutorClassroomAssignmentFragment(navController, code)
            }
        }
    }

}