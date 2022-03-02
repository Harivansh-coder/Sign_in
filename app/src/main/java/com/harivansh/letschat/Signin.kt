package com.harivansh.letschat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.harivansh.letschat.databinding.ActivitySigninBinding

class Signin : AppCompatActivity() {
    private var binding: ActivitySigninBinding? = null
    private var fauth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)
        fauth = FirebaseAuth.getInstance()


        // Login Button
        binding!!.signinbutton.setOnClickListener { SignIn() }


        // SignUp Button
        binding!!.signup.setOnClickListener {
            startActivity(Intent(this@Signin, SignUp::class.java))
            finish()
        }

        // Forgot Button
        binding!!.forgotLs.setOnClickListener {
            startActivity(Intent(this@Signin, ForgotPassword::class.java))
            finish()
        }
    }

    // Sign in function
    fun SignIn() {

        //binding.signinprogress.setVisibility(View.VISIBLE);
        val user_email = binding!!.userEmail.text.toString().trim()
        val user_password = binding!!.userPassword.text.toString().trim()
        if (user_email.length != 0 && user_password.length != 0) {
//             binding.signinprogress.setVisibility(View.INVISIBLE);
//             Toast.makeText(this,user_email+" "+user_password,Toast.LENGTH_LONG).show();
            fauth!!.signInWithEmailAndPassword(user_email, user_password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = FirebaseAuth.getInstance().currentUser
                        if (user!!.isEmailVerified) {
                            Log.d("message", "signin success")
                            Snackbar.make(
                                binding!!.signinbutton,
                                "signin successful",
                                BaseTransientBottomBar.LENGTH_LONG
                            ).show()
                            startActivity(Intent(this@Signin, DashBoard::class.java))
                            finish()
                        } else {
                            fauth!!.signOut()
                            Snackbar.make(
                                binding!!.signinbutton,
                                "please verify your self",
                                BaseTransientBottomBar.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        Log.d("error", "Signin failed", task.exception)
                        Snackbar.make(
                            binding!!.signinbutton,
                            task.exception!!.message!!, BaseTransientBottomBar.LENGTH_LONG
                        ).show()
                    }
                }
        } else Snackbar.make(
            binding!!.signinbutton,
            "either of the fields cannot be empty",
            BaseTransientBottomBar.LENGTH_LONG
        ).show()
    }
}