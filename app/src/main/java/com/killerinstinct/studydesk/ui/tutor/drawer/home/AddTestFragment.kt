package com.killerinstinct.studydesk.ui.tutor.drawer.home

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.FragmentAddTestBinding
import com.killerinstinct.studydesk.ui.tutor.TutorMainViewModel
import java.util.*

class AddTestFragment : Fragment(R.layout.fragment_add_test)
{

    var end_date_selected: String? = null
    var time: String? = null


    private lateinit var binding: FragmentAddTestBinding
    private val viewModel: TutorMainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddTestBinding.bind(view)

        binding.btnAdd.setOnClickListener{
            val navController = findNavController()
            viewModel.addTest(
                "Title",
                "Descrpfowenf",
                "DSA",
                "rOZ3S2",
                "123456",
                "09:12",
            )
            { isAdded ->
                if (isAdded)
                {
                    Toast.makeText(requireActivity(), "Test added", Toast.LENGTH_SHORT).show()
                    navController.navigateUp()
                } else
                {
                    Toast.makeText(requireActivity(), "Test not added", Toast.LENGTH_SHORT).show()
                }
            }

        }



        binding.dueDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar[Calendar.YEAR]
            val month = calendar[Calendar.MONTH]
            val date = calendar[Calendar.DATE]


            val datePickerDialog = DatePickerDialog(
                requireActivity()!!,
                { view, year, month, dayOfMonth ->
                    end_date_selected = dayOfMonth.toString() + "-" + (month + 1) + "-" + year
                    binding.dueDate.setText(end_date_selected)

                },
                year, month, date
            )
            datePickerDialog.show()

        }
        binding.dueTime.setOnClickListener{
            val calendar=Calendar.getInstance()
            val hour=calendar[Calendar.HOUR]
            val minute=calendar[Calendar.MINUTE]
            val ampm:String
            if( hour<12)
                ampm="AM"
            else
                ampm="PM"

            val timePickerDialog = TimePickerDialog(
                requireActivity()!!,
                {view,hour,minute->
                    time = hour.toString() + ":" + minute.toString()+" "+ampm
                    binding.dueTime.setText(time)
                },
                hour,minute, DateFormat.is24HourFormat(requireContext())
            )
            timePickerDialog.show()
        }
    }
}