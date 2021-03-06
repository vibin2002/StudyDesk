package com.killerinstinct.studydesk.adapters.dashboardAdaps

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.data.models.Assignment
import com.killerinstinct.studydesk.ui.student.drawer.dashboard.StudentDashBoardFragmentDirections

class DashStdAssignmentAdapter (
    private val navController: NavController,
    private var list:List<Assignment>,
    private val context: Context
): RecyclerView.Adapter<DashStdAssignmentAdapter.DashStdAssignmentViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DashStdAssignmentAdapter.DashStdAssignmentViewHolder {

        return DashStdAssignmentViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.assignment_card_rec, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DashStdAssignmentAdapter.DashStdAssignmentViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            val action = StudentDashBoardFragmentDirections.actionNavGalleryToSubmissionFragment2(
                list[position].title,
                list[position].description,
                list[position].date,
                list[position].time,
            )
            navController.navigate(action)
        }
        holder.assignmentName.text = list[position].title
        holder.sub_name.text = list[position].classRoomCode
        holder.dueDate.text = list[position].date
        holder.dueTime.text = list[position].time

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class DashStdAssignmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val assignmentName = view.findViewById<TextView>(R.id.assignment_name)
        val sub_name = view.findViewById<TextView>(R.id.assignment_subject_name)
        val dueDate= view.findViewById<TextView>(R.id.as_dueDate)
        val dueTime = view.findViewById<TextView>(R.id.as_dueTime)


    }
}