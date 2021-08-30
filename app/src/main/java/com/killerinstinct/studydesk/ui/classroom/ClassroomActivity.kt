package com.killerinstinct.studydesk.ui.classroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.ActivityClassroomBinding
import com.killerinstinct.studydesk.databinding.ActivityEntryBinding
import com.killerinstinct.studydesk.databinding.ClassCardViewBinding
import com.killerinstinct.studydesk.ui.tutor.drawer.dashboard.TutorViewPagerAdapter

class ClassroomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClassroomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classroom)


        val viewPagerAdapter = TutorViewPagerAdapter(this)
        binding.classroomViewpager.adapter = viewPagerAdapter
        TabLayoutMediator(
            binding.classroomAssTablayout, binding.classroomViewpager
        ) { tab, position ->
            when(position){
                0 -> tab.text = "Assignment"
                1 -> tab.text = "Test"
            }
        }.attach()
    }
}