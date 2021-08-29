package com.killerinstinct.studydesk.ui

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.ActivitySignupBinding
import com.killerinstinct.studydesk.ui.student.StudentMainActivity
import com.killerinstinct.studydesk.ui.tutor.TutorMainActivity
import com.killerinstinct.studydesk.viewmodel.SignUpViewModel

class SignupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding
    private val signUpViewModel: SignUpViewModel by viewModels()
    val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //dialog Code
        binding.googleSignUp.setOnClickListener(View.OnClickListener {
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.stu_or_tutor_dialog)
            dialog.setTitle("Confirm")
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            //                dialog.getWindow().setLayout(600,600);
            val tutor = dialog.findViewById<Button>(R.id.buttontutor)
            val student = dialog.findViewById<Button>(R.id.buttonStudent)
            tutor.setOnClickListener { v1: View? ->
                //TODO TUTOR SIGNUP
                dialog.dismiss()
            }
            student.setOnClickListener { v13: View? ->
                //TODO STUDENT SIGNUP
                dialog.dismiss()
            }
            dialog.show()
        })



        binding.infoSave.setOnClickListener {
            signUpViewModel.signUpWithCustomAuth(
                binding.suName.text.toString(),
                binding.suEmail.text.toString(),
                binding.suPassword.text.toString(),
                getDesignation()
            ) { signUpState ->
                when (signUpState) {
                    "Student" -> {
                        startActivity(Intent(this,StudentMainActivity::class.java))
                        finish()
                    }
                    "Tutor" -> {
                        startActivity(Intent(this,TutorMainActivity::class.java))
                        finish()
                    }
                    else -> {
                        Toast.makeText(this, signUpState, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.tvSignin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    private fun getDesignation(): String {
        val id = binding.radioGrp.checkedRadioButtonId
        return findViewById<RadioButton>(id).text.toString().trim()
    }
}