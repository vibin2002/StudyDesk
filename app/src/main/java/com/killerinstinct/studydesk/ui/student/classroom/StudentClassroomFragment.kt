package com.killerinstinct.studydesk.ui.student.classroom

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.FragmentStudentClassroomBinding

class StudentClassroomFragment : Fragment(R.layout.fragment_student_classroom) {

    lateinit var binding: FragmentStudentClassroomBinding
    var code = "NULL"
    var classRoomName = "NULL"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStudentClassroomBinding.bind(view)

        if(arguments !=null ) {
            val args: StudentClassroomFragmentArgs =
                StudentClassroomFragmentArgs.fromBundle(requireArguments())
            code = args.adapterPosition
            classRoomName = args.classRoomName
            Log.d("BugRoom", "Code $code")
        }else{
            Log.d("BugRoom", "NULL")
        }



        val viewPagerAdapter = StdClassroomViewPager(requireActivity(), code)
        binding.stdClsViewpager.adapter = viewPagerAdapter
        TabLayoutMediator(
            binding.stdClsTablayout, binding.stdClsViewpager
        ) { tab, position ->
            when(position){
                0 -> tab.text = "Assignment"
                1 -> tab.text = "Test"
            }
        }.attach()

    }

}