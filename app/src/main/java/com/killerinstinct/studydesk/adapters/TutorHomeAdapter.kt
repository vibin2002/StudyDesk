package com.killerinstinct.studydesk.adapters

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.data.models.ClassRoom
import com.killerinstinct.studydesk.ui.student.drawer.home.StudentHomeFragmentDirections
import com.killerinstinct.studydesk.ui.tutor.drawer.home.TutorHomeFragmentDirections

class TutorHomeAdapter(
    private val navController: NavController,
    private var list:List<ClassRoom>,
    private val context: Context
): RecyclerView.Adapter<TutorHomeAdapter.TutorHomeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TutorHomeAdapter.TutorHomeViewHolder {

        return TutorHomeViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.class_card_view,parent,false))
    }

    override fun onBindViewHolder(holder: TutorHomeAdapter.TutorHomeViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            val action = TutorHomeFragmentDirections.actionTutorHomeFragmentToTutorClassroomFragment(list[position].code)
            navController.navigate(action)
        }
        holder.class_name.text=list[position].className
        holder.tutorName.text=list[position].tutor
        holder.subject_name.text=list[position].subject
        holder.studentCount.text=list[position].students.size.toString()
        holder.btnShare.setOnClickListener {
            copyTextToClipboard(list[position].code)
        }
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
        val btnShare = view.findViewById<ImageView>(R.id.btn_share)

    }

    private fun copyTextToClipboard(textToCopy: String) {
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", textToCopy)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(context, "Classroom code copied to clipboard", Toast.LENGTH_LONG).show()
    }
}