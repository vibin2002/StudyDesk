package com.killerinstinct.studydesk.ui.tutor.drawer.home

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.adapters.TutorHomeAdapter
import com.killerinstinct.studydesk.data.models.ClassRoom
import com.killerinstinct.studydesk.databinding.FragmentTutorHomeBinding
import com.killerinstinct.studydesk.ui.tutor.TutorMainViewModel

class TutorHomeFragment : Fragment(R.layout.fragment_tutor_home) {

    private val viewModel: TutorMainViewModel by activityViewModels()
    lateinit var binding: FragmentTutorHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getTutorData{ hasTutor ->
            if (hasTutor){
//                Toast.makeText(requireActivity(), "Tutor fetched", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireActivity(), "Network Error", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun setupRecyclerView(classRoomList: List<ClassRoom>) {
        if(classRoomList.isEmpty()) {
           binding.tutHomeRv.visibility=View.GONE
            binding.emptyRv.visibility=View.VISIBLE
        }
        else
        {
            binding.emptyRv.visibility=View.GONE
            binding.tutHomeRv.visibility=View.VISIBLE
            binding.tutHomeRv.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = TutorHomeAdapter(findNavController(),classRoomList, requireActivity())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTutorHomeBinding.bind(view)

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