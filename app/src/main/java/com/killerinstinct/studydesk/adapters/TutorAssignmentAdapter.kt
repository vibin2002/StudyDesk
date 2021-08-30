package com.killerinstinct.studydesk.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.data.models.Assignment


class TutorAssignmentAdapter (

    private var list:List<Assignment>,
    private val context: Context
): RecyclerView.Adapter<TutorAssignmentAdapter.TutorAssignmentViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TutorAssignmentAdapter.TutorAssignmentViewHolder {

        return TutorAssignmentViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.assignment_card_rec, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TutorAssignmentAdapter.TutorAssignmentViewHolder, position: Int) {
        holder.assignmentName.text = list[position].title
        holder.sub_name.text = list[position].classRoomCode
        holder.dueDate.text = list[position].date
        holder.dueTime.text = list[position].time

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class TutorAssignmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val assignmentName = view.findViewById<TextView>(R.id.assignment_name)
        val sub_name = view.findViewById<TextView>(R.id.assignment_subject_name)
        val dueDate= view.findViewById<TextView>(R.id.as_dueDate)
        val dueTime = view.findViewById<TextView>(R.id.as_dueTime)


    }
}