package com.killerinstinct.studydesk.ui.tutor.classroom

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.FragmentStudentClassroomBinding
import com.killerinstinct.studydesk.databinding.FragmentTutorClassroomBinding
import com.killerinstinct.studydesk.ui.student.classroom.StdClassroomViewPager
import com.killerinstinct.studydesk.ui.student.classroom.StudentClassroomFragmentArgs
import com.killerinstinct.studydesk.ui.tutor.drawer.dashboard.TutorViewPagerAdapter

class TutorClassroomFragment : Fragment(R.layout.fragment_tutor_classroom) {

    lateinit var binding: FragmentTutorClassroomBinding

    var code = "NULL"
    var classRoomName = "NULL"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTutorClassroomBinding.bind(view)

        if(arguments !=null ) {
            val args: TutorClassroomFragmentArgs =
                TutorClassroomFragmentArgs.fromBundle(requireArguments())
            code = args.adapterPosition
            classRoomName = args.classRoomName
            Log.d("TutorBugRoom", "Code $code")
        }else{
            Log.d("TutorBugRoom", "NULL")
        }



        val viewPagerAdapter = TutorClassroomViewPager(findNavController(),requireActivity(), code)
        binding.tutClsViewpager.adapter = viewPagerAdapter
        TabLayoutMediator(
            binding.tutClsTablayout, binding.tutClsViewpager
        ) { tab, position ->
            when(position){
                0 -> tab.text = "Assignment"
                1 -> tab.text = "Test"
            }
        }.attach()

    }


}