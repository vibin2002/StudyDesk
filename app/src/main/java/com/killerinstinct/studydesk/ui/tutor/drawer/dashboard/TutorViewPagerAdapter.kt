package com.killerinstinct.studydesk.ui.tutor.drawer.dashboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.viewpager2.adapter.FragmentStateAdapter

class TutorViewPagerAdapter(
    private val navController: NavController,
    private val fragmentActivity: FragmentActivity
    ) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() :Int
    {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 ->{ TutorAssignmentFragment(navController)}
            1 -> {TutorTestFragment(navController)}
            2 -> {TutorCalendarFragment()}
            else -> {TutorDashBoardFragment()}
        }
    }

}