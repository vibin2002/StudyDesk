package com.killerinstinct.studydesk.ui.student.drawer.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.FragmentJoinClassBinding
import com.killerinstinct.studydesk.databinding.FragmentStudentHomeBinding
import com.killerinstinct.studydesk.ui.student.StudentMainViewModel

class JoinClassFragment : Fragment() {

    private var _binding: FragmentJoinClassBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StudentMainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnJoin.setOnClickListener{
            viewModel.addStudentToClass(binding.etClassCode.text.toString())
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJoinClassBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}