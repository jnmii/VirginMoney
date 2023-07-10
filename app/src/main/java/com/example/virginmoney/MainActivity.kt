package com.example.virginmoney

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.StatsLog.logEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var signInRequest: BeginSignInRequest
    private val TAG: String? = "MAIN_ACTIVITY"
    private lateinit var  analytics: FirebaseAnalytics
    private lateinit var auth:FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        var currentUser = auth.getCurrentUser()
        //updateUI(currentUser);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        analytics= Firebase.analytics
        auth = Firebase.auth
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder( GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        val emailInput = findViewById<EditText>(R.id.username)
        val passwordInput = findViewById<EditText>(R.id.password)
        val nameOfUser= findViewById<TextView>(R.id.tvName)



        findViewById<Button>(R.id.btnLogin).setOnClickListener {
          //  logEvent()
            val username = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            //assuming both are valid inputs
            auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        nameOfUser.text = user?.email
                        Toast.makeText(this,"Logged In!",Toast.LENGTH_SHORT)

//                        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
//                        navController.navigate(R.id.homeFragment)


                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        nameOfUser.text = task.exception?.localizedMessage
                    }
                }

        }
        findViewById<Button>(R.id.btnRegister).setOnClickListener {
            //  logEvent()
            val username = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            //assuming both are valid inputs
            auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        nameOfUser.text = user?.email
                        Toast.makeText(this,"Signed Up!",Toast.LENGTH_SHORT)

//                        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
//                        navController.navigate(R.id.homeFragment)
                    } else {
                        // If sign up fails, display a message to the user.
                        Log.w(TAG, "signUpWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        nameOfUser.text = task.exception?.localizedMessage
                    }
                }

        }

        findViewById<Button>(R.id.btnGoogle).setOnClickListener{
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)

        }

        }
    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }


    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        when (requestCode) {
//            REQ_ONE_TAP -> {
//                try {
//                    val credential = oneTapClient.getSignInCredentialFromIntent(data)
//                    val idToken = credential.googleIdToken
//                    when {
//                        idToken != null -> {
//                            // Got an ID token from Google. Use it to authenticate
//                            // with Firebase.
//                            Log.d(TAG, "Got ID token.")
//                        }
//                        else -> {
//                            // Shouldn't happen.
//                            Log.d(TAG, "No ID token!")
//                        }
//                    }
//                } catch (e: ApiException) {
//                    // ...
//                }
//            }
//        }
//        // ...
//    }

