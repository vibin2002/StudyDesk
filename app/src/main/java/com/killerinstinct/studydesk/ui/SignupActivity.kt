package com.killerinstinct.studydesk.ui

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.killerinstinct.studydesk.databinding.ActivitySignupBinding
import com.killerinstinct.studydesk.viewmodel.SignUpViewModel

class SignupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.infoSave.setOnClickListener {
            signUpViewModel.signUpWithCustomAuth(
                binding.suName.text.toString(),
                binding.suEmail.text.toString(),
                binding.suPassword.text.toString(),
                getDesignation()
            ) { signUpState ->
                Toast.makeText(this, signUpState, Toast.LENGTH_SHORT).show()
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