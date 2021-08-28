package com.killerinstinct.studydesk.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.killerinstinct.studydesk.R.*
import com.killerinstinct.studydesk.databinding.ActivityEntryBinding
import com.killerinstinct.studydesk.ui.student.StudentMainActivity


class EntryActivity : AppCompatActivity() {

    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var mAuth: FirebaseAuth? = null
    private lateinit var binding: ActivityEntryBinding

    override fun onStart() {
        super.onStart()
        val user = mAuth!!.currentUser
        if (user != null) {
            val intent = Intent(applicationContext, StudentMainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()
        createRequest()
        binding.googleSignIn.setOnClickListener {
            signIn()
        }
    }

    //
    private fun createRequest() {

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(string.default_web_client_id))
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
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = mAuth!!.currentUser

                    val intent = Intent(applicationContext, StudentMainActivity::class.java)
                    startActivity(intent)
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


}





