package com.killerinstinct.studydesk.ui.tutor.drawer.dashboard

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.adapters.dashboardAdaps.DashStdTestAdapter
import com.killerinstinct.studydesk.adapters.dashboardAdaps.DashTutTestAdapter
import com.killerinstinct.studydesk.data.models.Test
import com.killerinstinct.studydesk.databinding.FragmentTutorTestBinding
import com.killerinstinct.studydesk.ui.tutor.TutorMainViewModel

class TutorTestFragment(
    private val navController: NavController
) : Fragment(R.layout.fragment_tutor_test) {

    lateinit var binding: FragmentTutorTestBinding
    private val  viewModel: TutorMainViewModel by activityViewModels()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTutorTestBinding.bind(view)

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
            binding.tutTestsRv.visibility=View.GONE
            binding.emptyRv.visibility=View.VISIBLE
        }
        else {
            binding.emptyRv.visibility = View.GONE
            binding.tutTestsRv.visibility = View.VISIBLE
            binding.tutTestsRv.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = DashTutTestAdapter(navController,testsList, requireActivity())
            }
        }
    }

}