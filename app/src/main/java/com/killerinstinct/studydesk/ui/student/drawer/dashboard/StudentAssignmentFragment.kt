package com.killerinstinct.studydesk.ui.student.drawer.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.adapters.StudentAssignmentAdapter
import com.killerinstinct.studydesk.adapters.TutorAssignmentAdapter
import com.killerinstinct.studydesk.data.models.Assignment
import com.killerinstinct.studydesk.databinding.FragmentStudentAssignmentBinding
import com.killerinstinct.studydesk.ui.student.StudentMainViewModel


class StudentAssignmentFragment : Fragment(R.layout.fragment_student_assignment) {

    lateinit var binding: FragmentStudentAssignmentBinding
    private val viewModel: StudentMainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getAllAssignments{  gotAssignments ->
            if (gotAssignments){
                Log.d("gotAssignments", "Success")
                Toast.makeText(requireActivity(), "Assignments fetched", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("gotAssignments", "Failure")
                Toast.makeText(requireActivity(), "Unable to fetch Assignments", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStudentAssignmentBinding.bind(view)
        setupRecyclerView(listOf())

    }

    override fun onStart() {
        super.onStart()
        viewModel.assignments.observe(this){
            setupRecyclerView(it)
        }
    }

    private fun setupRecyclerView(assignmentList: List<Assignment>) {
        binding.stdAssignmentRv.apply {
            layoutManager= LinearLayoutManager(context)
            adapter= StudentAssignmentAdapter(assignmentList,requireActivity())
        }
    }

}