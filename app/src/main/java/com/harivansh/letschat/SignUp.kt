package com.harivansh.letschat

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.harivansh.letschat.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    private var binding: ActivitySignUpBinding? = null
    private var fauth: FirebaseAuth? = null
    private var progressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)

        // getting firebase instance
        fauth = FirebaseAuth.getInstance()

        // progress dialog
        progressDialog = ProgressDialog(this@SignUp)
        progressDialog!!.setTitle("Creating Account")
        progressDialog!!.setMessage("A moment please")


        // sign Up button
        binding!!.signupbutton.setOnClickListener { signUp() }
    }

    fun signUp() {
        progressDialog!!.show()

        // Getting user data
        val user_name = binding!!.signUpName.text.toString().trim()
        val user_email = binding!!.signUpEmail.text.toString().trim()
        val user_pass = binding!!.signUpPass.text.toString().trim()
        val user_conPass = binding!!.signUpConPass.text.toString().trim()
        if (user_name.length != 0 && user_email.length != 0 && user_pass.length != 0 && user_conPass.length != 0) {
            if (user_pass == user_conPass) {

                // user creation
                fauth!!.createUserWithEmailAndPassword(user_email, user_conPass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            progressDialog!!.dismiss()
                            verifyEmail(user_email)
                            Log.d("message", "signup success")
                            Snackbar.make(
                                binding!!.signupbutton,
                                "account created successfully, Now sign in",
                                BaseTransientBottomBar.LENGTH_LONG
                            ).show()
                        } else {
                            progressDialog!!.dismiss()
                            Log.d("error", "Signup failed", task.exception)
                            Snackbar.make(
                                binding!!.signupbutton,
                                task.exception!!.message!!, BaseTransientBottomBar.LENGTH_LONG
                            ).show()
                        }
                    }
            } else {
                progressDialog!!.dismiss()
                Snackbar.make(
                    binding!!.signupbutton,
                    "both password fields must be same",
                    BaseTransientBottomBar.LENGTH_LONG
                ).show()
            }
        } else {
            progressDialog!!.dismiss()
            Snackbar.make(
                binding!!.signupbutton,
                "either of the field cannot be empty",
                BaseTransientBottomBar.LENGTH_LONG
            ).show()
        }
    }

    fun verifyEmail(email: String?) {
        val user = fauth!!.currentUser
        user!!.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "Email sent.")
                    Snackbar.make(
                        binding!!.signupbutton,
                        "a verification has been sent to you",
                        BaseTransientBottomBar.LENGTH_LONG
                    ).show()
                }
            }
    }

    fun backButton(view: View?) {
        startActivity(Intent(this@SignUp, Signin::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}