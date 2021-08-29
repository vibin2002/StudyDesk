package com.killerinstinct.studydesk.ui.tutor.drawer.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.FragmentTutorDashBoardBinding
import com.killerinstinct.studydesk.ui.student.drawer.dashboard.StudentDashBoardViewModel

class TutorDashBoardFragment : Fragment() {
    private var _binding: FragmentTutorDashBoardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTutorDashBoardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter=TutorViewPagerAdapter(requireActivity().supportFragmentManager,lifecycle)
        binding.tutViewpager.adapter=adapter

        TabLayoutMediator(binding.tutTablayout,binding.tutViewpager){
            tab,position->
            when(position){
                0->{
                    tab.text="Assignment"
                }
                1->{
                    tab.text="Test"
                }
                2->{
                    tab.text="Calendar"
                }
            }
        }.attach()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

