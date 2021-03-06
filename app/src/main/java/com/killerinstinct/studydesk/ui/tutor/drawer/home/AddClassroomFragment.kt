package com.killerinstinct.studydesk.ui.tutor.drawer.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.FragmentAddClassroomBinding
import com.killerinstinct.studydesk.ui.tutor.TutorMainViewModel
import kotlin.math.log

class AddClassroomFragment : Fragment(R.layout.fragment_add_classroom) {

    private lateinit var binding: FragmentAddClassroomBinding
    private val viewModel: TutorMainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddClassroomBinding.bind(view)
        val navController=findNavController()

        binding.btnAdd.setOnClickListener {
            Log.d("addClassRoom:","Clicked")
            val tutor = viewModel.tutor.value
            if (tutor == null)
                Toast.makeText(requireContext(), "User null", Toast.LENGTH_SHORT).show()
            else {
                viewModel.addClassRoom(
                    binding.etClassname.text.toString(),
                    binding.etSubjectname.text.toString(),
                    viewModel.tutor.value!!.name
                ){ isAdded ->
                    if(isAdded)
                    {
                        Toast.makeText(requireActivity(), "Classroom added", Toast.LENGTH_SHORT).show()
                        navController.navigate(R.id.action_addClassroomFragment_to_tutorHomeFragment)
                    }
                    else{
                        Toast.makeText(requireActivity(), "Classroom not added", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }




    }

}