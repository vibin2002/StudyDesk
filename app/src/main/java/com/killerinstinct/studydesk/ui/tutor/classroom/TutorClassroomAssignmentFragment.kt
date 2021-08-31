package com.killerinstinct.studydesk.ui.tutor.classroom

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.adapters.StudentAssignmentAdapter
import com.killerinstinct.studydesk.adapters.TutorAssignmentAdapter
import com.killerinstinct.studydesk.data.models.Assignment
import com.killerinstinct.studydesk.databinding.FragmentStdClassroomAssignmentBinding
import com.killerinstinct.studydesk.databinding.FragmentTutorClassroomAssignmentBinding
import com.killerinstinct.studydesk.ui.student.StudentMainViewModel
import com.killerinstinct.studydesk.ui.tutor.TutorMainViewModel


class TutorClassroomAssignmentFragment(
    private val navController: NavController,
    private val code: String,
) : Fragment() {

    lateinit var binding: FragmentTutorClassroomAssignmentBinding
    private val viewModel: TutorMainViewModel by activityViewModels()

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTutorClassroomAssignmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.assignments.observe(viewLifecycleOwner) {
            setupRecyclerView(it.filter { assignment ->
                assignment.classRoomCode == code
            })
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.assignments.observe(this) {
            setupRecyclerView(it.filter { assignment ->
                assignment.classRoomCode == code
            })
        }
    }

    private fun setupRecyclerView(assignmentList: List<Assignment>) {
        binding.tutClsAssignmentRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TutorAssignmentAdapter(navController,assignmentList, requireActivity())
        }
    }
}