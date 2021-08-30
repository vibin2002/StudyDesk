package com.killerinstinct.studydesk.adapters

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.data.models.ClassRoom


class StudentHomeAdapter(

    private var list:List<ClassRoom>,
    private val context: Context
): RecyclerView.Adapter<StudentHomeAdapter.StudentHomeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentHomeAdapter.StudentHomeViewHolder {

        return StudentHomeViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.class_card_view,parent,false))
    }

    override fun onBindViewHolder(holder: StudentHomeAdapter.StudentHomeViewHolder, position: Int) {
        holder.class_name.text=list[position].className
        holder.studentName.text=list[position].tutor
        holder.subject_name.text=list[position].subject
        holder.studentCount.text=list[position].students.size.toString()
        holder.btnShare.setOnClickListener {
            copyTextToClipboard(list[position].code)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    inner class StudentHomeViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val studentName =view.findViewById<TextView>(R.id.staff_name)
        val class_name=view.findViewById<TextView>(R.id.class_name)
        val subject_name=view.findViewById<TextView>(R.id.subject_name)
        val studentCount=view.findViewById<TextView>(R.id.studnt_count)
        val btnShare = view.findViewById<ImageButton>(R.id.btn_share)

    }

    private fun copyTextToClipboard(textToCopy: String) {
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", textToCopy)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(context, "Classroom code copied to clipboard", Toast.LENGTH_LONG).show()
    }
}