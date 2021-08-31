package com.killerinstinct.studydesk.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.R.*
import com.killerinstinct.studydesk.data.models.LoginCheck
import com.killerinstinct.studydesk.data.models.Student
import com.killerinstinct.studydesk.data.models.Tutor
import com.killerinstinct.studydesk.databinding.ActivityEntryBinding
import com.killerinstinct.studydesk.ui.student.StudentMainActivity
import com.killerinstinct.studydesk.ui.tutor.TutorMainActivity
import kotlinx.coroutines.launch


class EntryActivity : AppCompatActivity() {

    private var mGoogleSignInClient: GoogleSignInClient? = null
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private lateinit var binding: ActivityEntryBinding

//    override fun onStart() {
//        super.onStart()
//        val user = mAuth.currentUser
//        if (user != null) {
//            db.collection("LoginCheck")
//                .document("LoginCheck")
//                .get().addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        val loginCheck = it.result.toObject(LoginCheck::class.java)
//                        when {
//                            loginCheck!!.students.contains(mAuth.currentUser?.uid) -> {
//                                Log.d("LoginChecker","Stuendt")
//                                val intent = Intent(applicationContext, StudentMainActivity::class.java)
//                                startActivity(intent)
//                            }
//                            loginCheck.tutors.contains(mAuth.currentUser?.uid) -> {
//                                Log.d("LoginChecker","Tutor")
//                                val intent = Intent(applicationContext, TutorMainActivity::class.java)
//                                startActivity(intent)
//                            }
//                            else -> {
//                                Toast.makeText(this, "Not a user", Toast.LENGTH_SHORT).show()
//                            }
//                        }
//                    } else {
//                        Toast.makeText(this, "Document not fetched", Toast.LENGTH_SHORT).show()
//                    }
//                }
//        }
//        else{
//            Toast.makeText(this, "User is null", Toast.LENGTH_SHORT).show()
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTheme(R.style.Theme_StudyDesk)
        createRequest()
        binding.emailSignIn.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }
        binding.googleSignIn.setOnClickListener {
            signIn()
        }
        binding.dontHav.setOnClickListener{
            startActivity(Intent(this,SignupActivity::class.java))
        }

    }

    //
    private fun createRequest() {

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(string.default_web_client_id1))
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient?.signInIntent ?: return
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(this, "Here ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = it.result.user?.uid
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
                                        }
                                        loginCheck.tutors.contains(mAuth.currentUser?.uid) -> {
                                            Log.d("LoginChecker","Tutor")
                                            val intent = Intent(applicationContext, TutorMainActivity::class.java)
                                            startActivity(intent)
                                        }
                                        else -> {
                                            Toast.makeText(this, "Not a user", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                } else {
                                    Toast.makeText(this, "Document not fetched", Toast.LENGTH_SHORT).show()
                                }
                            }
                    }

                    // Sign in success, update UI with the signed-in user's information
//                    val user = mAuth.currentUser
//                    val name = user?.displayName.toString()
//                    val designation = getDesignation()
//                    if (designation == "Student"){
//                        val student = Student(
//                            name, listOf(), listOf(), listOf()
//                        )
//                        mAuth.currentUser?.let { it1 ->
//                            db.collection("Students")
//                                .document(it1.uid)
//                                .set(student)
//                                .addOnSuccessListener {
//                                    Toast.makeText(this, "SignUp successful", Toast.LENGTH_SHORT).show()
//                                    val intent = Intent(applicationContext, StudentMainActivity::class.java)
//                                    startActivity(intent)
//                                }.addOnFailureListener {
//                                    Toast.makeText(this, "SignUp failure", Toast.LENGTH_SHORT).show()
//                                }
//                        }
//                    }else if(designation == "Tutor"){
//                        val tutor = Tutor(
//                            name, listOf(), listOf(), listOf()
//                        )
//                        mAuth.currentUser?.let { it1 ->
//                            db.collection("Tutors")
//                                .document(it1.uid)
//                                .set(tutor)
//                                .addOnSuccessListener {
//                                    Toast.makeText(this, "SignUp successful", Toast.LENGTH_SHORT).show()
//                                    val intent = Intent(applicationContext, TutorMainActivity::class.java)
//                                    startActivity(intent)
//                                }.addOnFailureListener {
//                                    Toast.makeText(this, "SignUp failure", Toast.LENGTH_SHORT).show()
//                                }
//                        }
//                    }

                } else {
                    Toast.makeText(this, "Sorry auth failed.", Toast.LENGTH_SHORT)
                        .show()
                }

                // ...
            }
    }

    companion object {
        private const val RC_SIGN_IN = 123
    }

    private fun getDesignation(): String {
        val id = binding.entryRadioGrp.checkedRadioButtonId
        return findViewById<RadioButton>(id).text.toString().trim()
    }

}