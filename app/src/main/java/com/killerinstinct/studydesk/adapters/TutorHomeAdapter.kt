package com.killerinstinct.studydesk.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.data.models.ClassRoom

class TutorHomeAdapter(
    private var list:List<ClassRoom>
): RecyclerView.Adapter<TutorHomeAdapter.TutorHomeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TutorHomeAdapter.TutorHomeViewHolder {

        return TutorHomeViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.class_card_view,parent,false))
    }

    override fun onBindViewHolder(holder: TutorHomeAdapter.TutorHomeViewHolder, position: Int) {
        holder.class_name.text=list[position].className
        holder.tutorName.text=list[position].tutor
        holder.subject_name.text=list[position].subject
        holder.studentCount.text=list[position].students.size.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }
    inner class TutorHomeViewHolder(view: View):RecyclerView.ViewHolder(view)
    {
        val tutorName=view.findViewById<TextView>(R.id.staff_name)
        val class_name=view.findViewById<TextView>(R.id.class_name)
        val subject_name=view.findViewById<TextView>(R.id.subject_name)
        val studentCount=view.findViewById<TextView>(R.id.studnt_count)

    }
}