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
import androidx.recyclerview.widget.LinearLayoutManager
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.adapters.StudentTestAdapter
import com.killerinstinct.studydesk.adapters.TutorTestAdapter
import com.killerinstinct.studydesk.data.models.Test
import com.killerinstinct.studydesk.databinding.FragmentStdClassroomTestBinding
import com.killerinstinct.studydesk.databinding.FragmentTutorClassroomTestBinding
import com.killerinstinct.studydesk.ui.student.StudentMainViewModel
import com.killerinstinct.studydesk.ui.tutor.TutorMainViewModel

class TutorClassroomTestFragment(
    private val navController: NavController,
    private val code: String,
) : Fragment() {

    lateinit var binding: FragmentTutorClassroomTestBinding

    private val viewModel: TutorMainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllTests { gotTests ->
            if (gotTests){
                Log.d("gotTests", "Success")
                Toast.makeText(requireActivity(), "Tests fetched", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("gotTests", "Failure")
                Toast.makeText(requireActivity(), "Unable to fetch Tests", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTutorClassroomTestBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.test.observe(viewLifecycleOwner) {
            setupRecyclerView(it.filter { assignment ->
                assignment.classRoomCode == code
            })
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.test.observe(this) {
            setupRecyclerView(it.filter { assignment ->
                assignment.classRoomCode == code
            })
        }
    }

    private fun setupRecyclerView(testList: List<Test>) {
        if(testList.isEmpty()) {
            binding.tutClsTestRv.visibility=View.GONE
            binding.emptyRv.visibility=View.VISIBLE
        }
        else {
            binding.emptyRv.visibility = View.GONE
            binding.tutClsTestRv.visibility = View.VISIBLE
            binding.tutClsTestRv.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = TutorTestAdapter(navController,testList, requireActivity())
            }
        }
    }


}