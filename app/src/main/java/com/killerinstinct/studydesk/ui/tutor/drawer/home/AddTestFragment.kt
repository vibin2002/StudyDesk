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
import com.killerinstinct.studydesk.databinding.FragmentAddTestBinding
import com.killerinstinct.studydesk.ui.tutor.TutorMainViewModel

class AddTestFragment : Fragment(R.layout.fragment_add_test) {

    private lateinit var binding: FragmentAddTestBinding
    private val viewModel: TutorMainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddTestBinding.bind(view)

        val navController = findNavController()
        viewModel.addTest(
            "Title",
            "Descrpfowenf",
            "DSA",
            "rOZ3S2",
            "123456",
            "09:12",
        ) { isAdded ->
            if (isAdded) {
                Toast.makeText(requireActivity(), "Test added", Toast.LENGTH_SHORT).show()
                navController.navigateUp()
            } else {
                Toast.makeText(requireActivity(), "Test not added", Toast.LENGTH_SHORT).show()
            }

        }

    }


}