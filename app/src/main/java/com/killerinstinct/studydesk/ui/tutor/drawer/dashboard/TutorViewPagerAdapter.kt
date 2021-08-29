package com.killerinstinct.studydesk.ui.tutor.drawer.dashboard

import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TutorViewPagerAdapter(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() :Int
    {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 ->{ TutorAssignmentFragment()}
            1 -> {TutorTestFragment()}
            2 -> {TutorCalendarFragment()}
            else -> {TutorDashBoardFragment()}
        }
    }

}