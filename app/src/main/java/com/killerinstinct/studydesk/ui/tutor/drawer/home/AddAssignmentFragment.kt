package com.killerinstinct.studydesk.ui.tutor.drawer.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.FragmentAddAssignmentBinding
import com.killerinstinct.studydesk.ui.tutor.TutorMainViewModel

class AddAssignmentFragment : Fragment(R.layout.fragment_add_assignment) {

    private lateinit var binding: FragmentAddAssignmentBinding
    private val viewModel: TutorMainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddAssignmentBinding.bind(view)

        val navController = findNavController()
        viewModel.addAssignment(
            "Title",
            "Descrpfowenf",
            "rOZ3S2",
            "123456",
            "09:12",
            ) { isAdded ->
            if (isAdded) {
                Toast.makeText(requireActivity(), "Assignment added", Toast.LENGTH_SHORT).show()
                navController.navigateUp()
            } else {
                Toast.makeText(requireActivity(), "Assignment not added", Toast.LENGTH_SHORT).show()
            }

        }

    }

}