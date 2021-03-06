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
import com.killerinstinct.studydesk.adapters.StudentTestAdapter
import com.killerinstinct.studydesk.adapters.dashboardAdaps.DashStdTestAdapter
import com.killerinstinct.studydesk.data.models.Test
import com.killerinstinct.studydesk.databinding.FragmentStudentTestBinding
import com.killerinstinct.studydesk.ui.student.StudentMainViewModel

class StudentTestFragment(
    private val navController: NavController,
) : Fragment(R.layout.fragment_student_test) {

    lateinit var binding: FragmentStudentTestBinding
    private val viewModel: StudentMainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getAllTests { gotTests ->
            if (gotTests){
                Log.d("gotTests", "Success")
//                Toast.makeText(requireActivity(), "Tests fetched", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("gotTests", "Failure")
                Toast.makeText(requireActivity(), "Unable to fetch Tests", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStudentTestBinding.bind(view)

        setUpRecyclerView(listOf())

    }

    override fun onStart() {
        super.onStart()
        viewModel.test.observe(this){
            setUpRecyclerView(it)
        }
    }

    private fun setUpRecyclerView(testsList: List<Test>){
        if(testsList.isEmpty()) {
            binding.stdTestRv.visibility=View.GONE
            binding.emptyRv.visibility=View.VISIBLE
        }
        else {
            binding.emptyRv.visibility = View.GONE
            binding.stdTestRv.visibility = View.VISIBLE
            binding.stdTestRv.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = DashStdTestAdapter(navController,testsList, requireActivity())
            }
        }
    }

}