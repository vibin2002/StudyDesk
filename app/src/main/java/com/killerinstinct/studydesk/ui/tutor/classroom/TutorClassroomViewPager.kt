package com.killerinstinct.studydesk.ui.tutor.classroom

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.killerinstinct.studydesk.ui.student.classroom.StdClassroomAssignmentFragment
import com.killerinstinct.studydesk.ui.student.classroom.StdClassroomTestFragment

class TutorClassroomViewPager(
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
                TutorClassroomAssignmentFragment(code)
            }
            1 -> {
                TutorClassroomTestFragment(code)
            }
            else -> {
                TutorClassroomAssignmentFragment(code)
            }
        }
    }

}