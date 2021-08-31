package com.killerinstinct.studydesk.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.data.models.LoginCheck
import com.killerinstinct.studydesk.ui.student.StudentMainActivity
import com.killerinstinct.studydesk.ui.tutor.TutorMainActivity

class SplashScreenActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)



    }

    override fun onStart() {
        super.onStart()
        val user = mAuth.currentUser
        if (user != null) {
            db.collection("LoginCheck")
                .document("LoginCheck")
                .get().addOnCompleteListener {
                    if (it.isSuccessful) {
                        val loginCheck = it.result.toObject(LoginCheck::class.java)
                        when {
                            loginCheck!!.students.contains(mAuth.currentUser?.uid) -> {
                                Log.d("LoginChecker","Stuendt")
                                val intent = Intent(applicationContext, StudentMainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            loginCheck.tutors.contains(mAuth.currentUser?.uid) -> {
                                Log.d("LoginChecker","Tutor")
                                val intent = Intent(applicationContext, TutorMainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            else -> {
                                val intent = Intent(applicationContext, EntryActivity::class.java)
                                startActivity(intent)
                                finish()
                                Toast.makeText(this, "Not a user", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        val intent = Intent(applicationContext, EntryActivity::class.java)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this, "Document not fetched", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        else{
            val intent = Intent(applicationContext, EntryActivity::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this, "User is null", Toast.LENGTH_SHORT).show()
        }
    }

}