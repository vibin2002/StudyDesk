package com.killerinstinct.studydesk.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.databinding.ActivitySignupBinding
import com.killerinstinct.studydesk.viewmodel.SignUpViewModel

class SignupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding
    val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.infoSave.setOnClickListener {
            signUpViewModel.signUpWithCustomAuth(
                binding.tutorEmail.text.toString(),
                binding.tutorPasswordr.text.toString()
            )
        }

        signUpViewModel.isUserCreated.observe(this,{
            if (it){
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }
        })

    }
}