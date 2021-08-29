package com.killerinstinct.studydesk.ui.student.drawer.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.FragmentStudentDashboardBinding
import com.killerinstinct.studydesk.ui.tutor.drawer.dashboard.TutorViewPagerAdapter

class StudentDashBoardFragment : Fragment(R.layout.fragment_tutor_dash_board) {

    private lateinit var dashBoardViewModelStudent: StudentDashBoardViewModel
    private var _binding: FragmentStudentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashBoardViewModelStudent =
            ViewModelProvider(this).get(StudentDashBoardViewModel::class.java)

        _binding = FragmentStudentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewPagerAdapter = TutorViewPagerAdapter(requireActivity())
        binding.viewpager.adapter = viewPagerAdapter
        TabLayoutMediator(
            binding.tablayout, binding.viewpager
        ) { tab, position ->
            tab.text = "Tab $position"

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}