package com.harivansh.letschat

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.harivansh.letschat.databinding.ActivityForgotPasswordBinding

class ForgotPassword : AppCompatActivity() {
    private var binding: ActivityForgotPasswordBinding? = null
    private var firebaseAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)
        firebaseAuth = FirebaseAuth.getInstance()

        // forgot password
        binding!!.sendFps.setOnClickListener {
            val email = binding!!.emailfps.text.toString().trim()
            if (email.length != 0) {
                firebaseAuth!!.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Snackbar.make(
                            binding!!.sendFps, "Check email to reset password",
                            BaseTransientBottomBar.LENGTH_LONG
                        ).show()
                    } else {
                        Snackbar.make(
                            binding!!.sendFps, task.exception!!.message!!,
                            BaseTransientBottomBar.LENGTH_LONG
                        ).show()
                    }
                }
            } else {
                Snackbar.make(
                    binding!!.sendFps, "email cannot be empty",
                    BaseTransientBottomBar.LENGTH_LONG
                ).show()
            }
        }


        // back button
        binding!!.BackFps.setOnClickListener {
            startActivity(Intent(this@ForgotPassword, Signin::class.java))
            finish()
        }
    }
}