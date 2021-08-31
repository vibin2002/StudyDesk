package com.killerinstinct.studydesk.ui.tutor.drawer.home

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.FragmentAddAssignmentBinding
import com.killerinstinct.studydesk.ui.tutor.TutorMainViewModel
import java.util.*

class AddAssignmentFragment : Fragment(R.layout.fragment_add_assignment)
{

    var end_date_selected: String? = null
    var time:String?=null

    private lateinit var binding: FragmentAddAssignmentBinding
    private val viewModel: TutorMainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddAssignmentBinding.bind(view)

        binding.btnAdd.setOnClickListener {
            val navController = findNavController()
            viewModel.addAssignment(
                binding.assignmentTitle.text.toString(),
                binding.assignmentDescription.text.toString(),
                binding.assignmentClassCode.text.toString(),
                end_date_selected ?: "No due date",
                time ?: "No due time",
            ) { isAdded ->
                if (isAdded) {
                    Toast.makeText(requireActivity(), "Assignment added", Toast.LENGTH_SHORT).show()
                    navController.navigateUp()
                } else {
                    Toast.makeText(requireActivity(), "Assignment not added", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }



            binding.assDueDate.setOnClickListener{
                val calendar = Calendar.getInstance()
                val year = calendar[Calendar.YEAR]
                val month = calendar[Calendar.MONTH]
                val date = calendar[Calendar.DATE]


                val datePickerDialog = DatePickerDialog(
                    requireActivity()!!,
                    { view, year, month, dayOfMonth ->
                        end_date_selected = dayOfMonth.toString() + "-" + (month + 1) + "-" + year
                        binding.assDueDate.setText(end_date_selected)

                    },
                    year, month, date
                )
                datePickerDialog.show()
            }
            binding.assDueTime.setOnClickListener{
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
                        binding.assDueTime.setText(time)
                    },
                    hour,minute,DateFormat.is24HourFormat(requireContext())
                )
                timePickerDialog.show()
            }
        }
    }


