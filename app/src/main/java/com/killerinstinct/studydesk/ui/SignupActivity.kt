package com.killerinstinct.studydesk.ui

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.killerinstinct.studydesk.R
import com.killerinstinct.studydesk.data.models.Student
import com.killerinstinct.studydesk.data.models.Tutor
import com.killerinstinct.studydesk.databinding.ActivitySignupBinding
import com.killerinstinct.studydesk.ui.student.StudentMainActivity
import com.killerinstinct.studydesk.ui.tutor.TutorMainActivity
import com.killerinstinct.studydesk.viewmodel.SignUpViewModel

class SignupActivity : AppCompatActivity() {

    private var mGoogleSignInClient: GoogleSignInClient? = null
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    var desig = "NULL"
    private lateinit var progressDialog: ProgressDialog
    lateinit var binding: ActivitySignupBinding
    private val signUpViewModel: SignUpViewModel by viewModels()
    val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)

        createRequest()
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
                dialog.dismiss()
                desig = "Tutor"
                signIn()
            }
            student.setOnClickListener { v13: View? ->
                dialog.dismiss()
                desig = "Student"
                signIn()
            }
            dialog.show()
        })



        binding.infoSave.setOnClickListener {

            //if the fields are empty
            if (TextUtils.isEmpty(binding.suName.toString()) || TextUtils.isEmpty(binding.suEmail.toString())
                || TextUtils.isEmpty(binding.suPassword.toString())) {
                Toast.makeText(this, "please fill the field!", Toast.LENGTH_SHORT).show()
            } else {
                progressDialog!!.setMessage("Loging Please wait")

                progressDialog!!.show()


                signUpViewModel.signUpWithCustomAuth(
                    binding.suName.text.toString(),
                    binding.suEmail.text.toString(),
                    binding.suPassword.text.toString(),
                    getDesignation()
                ) { signUpState ->
                    when (signUpState) {
                        "Student" -> {
                            progressDialog!!.dismiss()
                            startActivity(Intent(this, StudentMainActivity::class.java))
                            finish()
                        }
                        "Tutor" -> {
                            progressDialog!!.dismiss()
                            startActivity(Intent(this, TutorMainActivity::class.java))
                            finish()
                        }
                        else -> {
                            progressDialog!!.dismiss()
                            Toast.makeText(this, signUpState, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        binding.tvSignin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    private fun createRequest() {

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id1))
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
                    // Sign in success, update UI with the signed-in user's information
                    val user = mAuth.currentUser
                    val name = user?.displayName.toString()
                    val designation = desig
                    if (designation == "Student"){
                        val student = Student(
                            name, listOf(), listOf(), listOf()
                        )
                        mAuth.currentUser?.let { it1 ->
                            db.collection("Students")
                                .document(it1.uid)
                                .set(student)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "SignUp successful", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(applicationContext, StudentMainActivity::class.java)
                                    startActivity(intent)
                                }.addOnFailureListener {
                                    Toast.makeText(this, "SignUp failure", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }else if(designation == "Tutor"){
                        val tutor = Tutor(
                            name, listOf(), listOf(), listOf()
                        )
                        mAuth.currentUser?.let { it1 ->
                            db.collection("Tutors")
                                .document(it1.uid)
                                .set(tutor)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "SignUp successful", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(applicationContext, TutorMainActivity::class.java)
                                    startActivity(intent)
                                }.addOnFailureListener {
                                    Toast.makeText(this, "SignUp failure", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }

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
        val id = binding.radioGrp.checkedRadioButtonId
        return findViewById<RadioButton>(id).text.toString().trim()
    }
}