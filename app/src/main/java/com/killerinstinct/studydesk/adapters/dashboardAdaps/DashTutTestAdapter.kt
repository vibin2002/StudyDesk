package com.killerinstinct.studydesk.adapters.dashboardAdaps

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.data.models.Test
import com.killerinstinct.studydesk.ui.tutor.drawer.dashboard.TutorDashBoardFragmentDirections

class DashTutTestAdapter (
    private val navController: NavController,
    private var list:List<Test>,
    private val context: Context
): RecyclerView.Adapter<DashTutTestAdapter.DashTutTestViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DashTutTestAdapter.DashTutTestViewHolder {

        return DashTutTestViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.test_card_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DashTutTestAdapter.DashTutTestViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val action = TutorDashBoardFragmentDirections.actionTutorDashBoardFragmentToSubmissionFragment(
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

    inner class DashTutTestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.test_name)
        val sub_name = view.findViewById<TextView>(R.id.test_subject_name)
        val dueDate= view.findViewById<TextView>(R.id.test_dueDate)
        val dueTime = view.findViewById<TextView>(R.id.test_dueTime)

    }
}