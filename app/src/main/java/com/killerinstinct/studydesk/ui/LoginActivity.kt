package com.killerinstinct.studydesk.ui

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.viewModels
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.ActivityLoginBinding
import com.killerinstinct.studydesk.ui.student.StudentMainActivity
import com.killerinstinct.studydesk.ui.tutor.TutorMainActivity
import com.killerinstinct.studydesk.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private lateinit var progressDialog: ProgressDialog
    val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)

        binding.buttonLogin.setOnClickListener {


            if (TextUtils.isEmpty(binding.loginEmail.toString()) || TextUtils.isEmpty(binding.loginPassword.toString())) {
                Toast.makeText(this, "please fill the field!", Toast.LENGTH_SHORT).show()
            } else {
                progressDialog!!.setMessage("Loging Please wait")

                progressDialog!!.show()

                loginViewModel.loginUser(
                    binding.loginEmail.text.toString(),
                    binding.loginPassword.text.toString()
                ) { isValid ->
                    if (isValid == "Student") {
                        progressDialog!!.dismiss()
                        Toast.makeText(this, "login Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, StudentMainActivity::class.java))
                    } else if (isValid == "Tutor") {
                        progressDialog!!.dismiss()
                        Toast.makeText(this, "login Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, TutorMainActivity::class.java))
                    } else if (isValid == "Failure") {
                        progressDialog!!.dismiss()
                        Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

    }
}