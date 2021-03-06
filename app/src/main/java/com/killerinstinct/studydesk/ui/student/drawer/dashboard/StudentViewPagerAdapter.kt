package com.killerinstinct.studydesk.ui.student.drawer.dashboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.killerinstinct.studydesk.ui.tutor.drawer.dashboard.TutorDashBoardFragment

class StudentViewPagerAdapter(
    private val navController: NavController,
    private val fragmentActivity: FragmentActivity
    ) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                StudentAssignmentFragment(navController)
            }
            1 -> {
                StudentTestFragment(navController)
            }
            2 -> {
                StudentCalendarFragment()
            }
            else -> {
                StudentAssignmentFragment(navController)
            }
        }
    }

}