package com.killerinstinct.studydesk.ui.tutor.drawer.dashboard

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.adapters.TutorAssignmentAdapter
import com.killerinstinct.studydesk.data.models.Assignment
import com.killerinstinct.studydesk.databinding.FragmentTutorAssignmentBinding
import com.killerinstinct.studydesk.ui.tutor.TutorMainViewModel

class TutorAssignmentFragment : Fragment(R.layout.fragment_tutor_assignment) {

    lateinit var binding: FragmentTutorAssignmentBinding
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTutorAssignmentBinding.bind(view)
        setupRecyclerView(listOf())
    }

    override fun onStart() {
        super.onStart()
        viewModel.assignments.observe(this){
            setupRecyclerView(it)
        }
    }

    private fun setupRecyclerView(assignmentList: List<Assignment>) {
        if(assignmentList.isEmpty()) {
            binding.tutAssignmentRv.visibility=View.GONE
            binding.emptyRv.visibility=View.VISIBLE
        }
        else {
            binding.emptyRv.visibility = View.GONE
            binding.tutAssignmentRv.visibility = View.VISIBLE
            binding.tutAssignmentRv.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = TutorAssignmentAdapter(assignmentList, requireActivity())
            }
        }
    }

}