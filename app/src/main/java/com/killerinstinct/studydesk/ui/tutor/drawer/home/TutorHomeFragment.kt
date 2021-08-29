package com.killerinstinct.studydesk.ui.tutor.drawer.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.FragmentTutorHomeBinding
import com.killerinstinct.studydesk.ui.tutor.TutorMainViewModel

class TutorHomeFragment : Fragment(R.layout.fragment_tutor_home) {

    private val viewModel: TutorMainViewModel by activityViewModels()
    lateinit var binding: FragmentTutorHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getTutorData{ hasTutor ->
            if (hasTutor){
                Toast.makeText(requireActivity(), "Tutor fetched", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireActivity(), "Unable to fetch tutor", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTutorHomeBinding.bind(view)

        binding.tutHomeFab.setOnClickListener {
            val viewGroup = binding.fragTutHome
            val dialogView = LayoutInflater.from(requireActivity()).inflate(R.layout.fragment_add_classroom,viewGroup,false)
            val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
            builder.setView(dialogView)
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }



    }

}