package com.killerinstinct.studydesk.ui.tutor.drawer.home

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.FragmentAddAssignmentBinding
import com.killerinstinct.studydesk.ui.tutor.TutorMainViewModel
import java.util.*

class AddAssignmentFragment : Fragment(R.layout.fragment_add_assignment)
//    DatePickerDialog.OnDateSetListener,
//    TimePickerDialog.OnTimeSetListener
{

//    var day=0
//    var month=0
//    var year=0
//    var hour=0
//    var minute=0
//    var Savday=0
//    var Savmonth=0
//    var Savyear=0
//    var Savhour=0
//    var Savminute=0


    private lateinit var binding: FragmentAddAssignmentBinding
    private val viewModel: TutorMainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddAssignmentBinding.bind(view)

        val navController = findNavController()
        binding.btnAdd.setOnClickListener {
            viewModel.addAssignment(
                binding.assignmentTitle.text.toString(),
                binding.assignmentDescription.text.toString(),
                binding.assignmentClassCode.text.toString(),
                "123456",
                "09:12",
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


//        binding.dueDate.setOnClickListener{
//            getDateTime()
//            DatePickerDialog(requireActivity(),this,year,month,day)
//        }
//        binding.dueTime.setOnClickListener{
//            getDateTime()
//            TimePickerDialog(requireActivity(),this,hour,minute,true)
//        }
    }

//    private  fun getDateTime()
//    {
//        val cal:Calendar=Calendar.getInstance()
//        day=cal.get(Calendar.DAY_OF_MONTH)
//        month=cal.get(Calendar.MONTH)
//        year=cal.get(Calendar.YEAR)
//        hour=cal.get(Calendar.HOUR)
//        minute=cal.get(Calendar.MINUTE)
//
//    }

//    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//        Savday=dayOfMonth
//        Savmonth=month
//        Savyear=year
//
//        getDateTime()
//
//        binding.dueDate.text="$Savday-$Savmonth-$Savyear"
//    }

//    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
//
//        Savhour=hourOfDay
//        Savminute=minute
//
//        getDateTime()
//
//        binding.dueTime.text="$Savhour:$Savminute"
//    }

}