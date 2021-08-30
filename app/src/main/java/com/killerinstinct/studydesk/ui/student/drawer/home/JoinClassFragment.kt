package com.killerinstinct.studydesk.ui.student.drawer.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.FragmentJoinClassBinding
import com.killerinstinct.studydesk.databinding.FragmentStudentHomeBinding
import com.killerinstinct.studydesk.ui.student.StudentMainViewModel

class JoinClassFragment : Fragment(R.layout.fragment_join_class) {

    lateinit var binding: FragmentJoinClassBinding
    private val viewModel: StudentMainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentJoinClassBinding.bind(view)
        binding.btnJoin.setOnClickListener{
            viewModel.addStudentToClass(binding.etClassCode.text.toString()){
                when (it){
                    "Success" -> {
                        Toast.makeText(requireActivity(), "Success", Toast.LENGTH_SHORT).show()
                    }
                    "Failure" -> {
                        Toast.makeText(requireActivity(), "Failure", Toast.LENGTH_SHORT).show()
                    }
                    "Invalid" -> {
                        Toast.makeText(requireActivity(), "Invalid Code", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


}