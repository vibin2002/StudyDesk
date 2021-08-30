package com.killerinstinct.studydesk.ui.classroom

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.killerinstinct.studydesk.ui.tutor.drawer.dashboard.TutorAssignmentFragment
import com.killerinstinct.studydesk.ui.tutor.drawer.dashboard.TutorCalendarFragment
import com.killerinstinct.studydesk.ui.tutor.drawer.dashboard.TutorDashBoardFragment
import com.killerinstinct.studydesk.ui.tutor.drawer.dashboard.TutorTestFragment


class ClassroomViewPager(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() :Int
    {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 ->{ AssignmentFragment()
            }
            1 -> {
                ClassroomTestFragment()
            }
            else -> {
                AssignmentFragment()
            }
        }
    }

}