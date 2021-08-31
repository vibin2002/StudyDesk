package com.killerinstinct.studydesk.ui.student.drawer.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.adapters.StudentHomeAdapter
import com.killerinstinct.studydesk.adapters.TutorHomeAdapter
import com.killerinstinct.studydesk.data.models.ClassRoom
import com.killerinstinct.studydesk.databinding.FragmentStudentHomeBinding
import com.killerinstinct.studydesk.databinding.FragmentTutorHomeBinding
import com.killerinstinct.studydesk.ui.student.StudentMainViewModel

class StudentHomeFragment : Fragment(R.layout.fragment_student_home) {

    lateinit var binding: FragmentStudentHomeBinding
    private val viewModel: StudentMainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getStudentData{ hasStudent ->
            if (hasStudent){
//                Toast.makeText(requireActivity(), "Student fetched", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireActivity(), "Network Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView(classRoomList: List<ClassRoom>) {
        if(classRoomList.isEmpty()) {
            binding.stdHomeRv.visibility=View.GONE
            binding.emptyRv.visibility=View.VISIBLE
        }
        else {
            binding.emptyRv.visibility = View.GONE
            binding.stdHomeRv.visibility = View.VISIBLE
            binding.stdHomeRv.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = StudentHomeAdapter(findNavController(),classRoomList, requireActivity())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStudentHomeBinding.bind(view)

        val navController = findNavController()
        binding.stdHomeFab.setOnClickListener {
            navController.navigate(R.id.action_nav_home_to_joinClassFragment)
        }
        setupRecyclerView(listOf())

    }

    override fun onStart() {
        super.onStart()
        viewModel.classRooms.observe(this){
            setupRecyclerView(it)
        }
        Log.d("LOkiSylvie", "onStart: SUmmma")
    }

}