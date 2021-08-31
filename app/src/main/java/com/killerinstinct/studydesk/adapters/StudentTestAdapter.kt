package com.killerinstinct.studydesk.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.data.models.Test
import com.killerinstinct.studydesk.ui.student.classroom.StudentClassroomFragmentDirections


class StudentTestAdapter (
    private val navController: NavController,
    private var list:List<Test>,
    private val context: Context
): RecyclerView.Adapter<StudentTestAdapter.StudentTestViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentTestAdapter.StudentTestViewHolder {

        return StudentTestViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.test_card_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StudentTestAdapter.StudentTestViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val action = StudentClassroomFragmentDirections.actionStudentClassroomFragment2ToSubmissionFragment2(
                list[position].title,
                list[position].subject,
                list[position].date ,
                list[position].time
            )
            navController.navigate(action)
        }
        holder.name.text = list[position].title
        holder.sub_name.text = list[position].subject
        holder.dueDate.text = list[position].date
        holder.dueTime.text = list[position].time

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class StudentTestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.test_name)
        val sub_name = view.findViewById<TextView>(R.id.test_subject_name)
        val dueDate= view.findViewById<TextView>(R.id.test_dueDate)
        val dueTime = view.findViewById<TextView>(R.id.test_dueTime)

    }
}