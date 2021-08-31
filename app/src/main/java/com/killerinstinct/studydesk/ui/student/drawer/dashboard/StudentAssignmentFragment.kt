package com.killerinstinct.studydesk.ui.student.drawer.dashboard

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.adapters.StudentAssignmentAdapter
import com.killerinstinct.studydesk.adapters.dashboardAdaps.DashStdAssignmentAdapter
import com.killerinstinct.studydesk.data.models.Assignment
import com.killerinstinct.studydesk.databinding.FragmentStudentAssignmentBinding
import com.killerinstinct.studydesk.ui.student.StudentMainViewModel


class StudentAssignmentFragment(
    private val navController: NavController,
) : Fragment(R.layout.fragment_student_assignment) {

    lateinit var binding: FragmentStudentAssignmentBinding
    private val viewModel: StudentMainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getAllAssignments{  gotAssignments ->
            if (gotAssignments){
                Log.d("gotAssignments", "Success")
//                Toast.makeText(requireActivity(), "Assignments fetched", Toast.LENGTH_SHORT).show()
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
        viewModel.assignments.observe(this) {
            setupRecyclerView(it)
        }
    }

    private fun setupRecyclerView(assignmentList: List<Assignment>) {
        if(assignmentList.isEmpty()) {
            binding.stdAssignmentRv.visibility=View.GONE
            binding.emptyRv.visibility=View.VISIBLE
        }
        else {
            binding.emptyRv.visibility = View.GONE
            binding.stdAssignmentRv.visibility = View.VISIBLE
            binding.stdAssignmentRv.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = DashStdAssignmentAdapter(navController, assignmentList, requireActivity())
            }
        }
    }

}