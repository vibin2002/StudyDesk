package com.killerinstinct.studydesk.ui.student.drawer.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.killerinstinct.studydesk.databinding.FragmentStudentHomeBinding

class StudentHomeFragment : Fragment() {

  private lateinit var studentHomeViewModel: StudentHomeViewModel
private var _binding: FragmentStudentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    studentHomeViewModel =
            ViewModelProvider(this).get(StudentHomeViewModel::class.java)

    _binding = FragmentStudentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textHome
    studentHomeViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}