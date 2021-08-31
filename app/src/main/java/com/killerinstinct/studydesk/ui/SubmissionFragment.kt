package com.killerinstinct.studydesk.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.killerinstinct.studydesk.databinding.FragmentSubmissionBinding

class SubmissionFragment : Fragment() {

    lateinit var binding: FragmentSubmissionBinding
    val args: SubmissionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubmissionBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.subTitle.text = args.title
        binding.subDescription.text = args.description
        binding.subDueDate.text = args.dueDate
        binding.subDueTime.text = args.dueTime

    }



}