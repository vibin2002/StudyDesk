package com.killerinstinct.studydesk.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.ActivityLoginBinding
import com.killerinstinct.studydesk.ui.student.StudentMainActivity
import com.killerinstinct.studydesk.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            loginViewModel.loginUser(
                binding.loginEmail.text.toString(),
                binding.loginPassword.text.toString()
            ) { isValid ->
                if(isValid == "Student"){
                    Toast.makeText(this, "login Successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, StudentMainActivity::class.java))
                }else if(isValid == "Tutor"){
                    Toast.makeText(this, "login Successful", Toast.LENGTH_SHORT).show()
//                    startActivity(Intent(this, TutorMainActivity::class.java))
                }else if(isValid == "Failure"){
                    Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show()
                }

            }
        }

        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

    }
}