package com.harivansh.letschat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.harivansh.letschat.databinding.ActivityDashBoardBinding

class DashBoard : AppCompatActivity() {
    private var binding: ActivityDashBoardBinding? = null
    private var fauth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        // firebase instance
        fauth = FirebaseAuth.getInstance()


        //sign out button
        binding!!.logout.setOnClickListener { signOut() }
    }

    // signout function
    fun signOut() {
        val builder1 = AlertDialog.Builder(this@DashBoard)
        builder1.setMessage("Do you really want to sign out")
        builder1.setCancelable(true)
        builder1.setPositiveButton(
            "Yes"
        ) { dialog, id ->
            fauth!!.signOut()
            startActivity(Intent(this@DashBoard, Signin::class.java))
            finish()
        }
        builder1.setNegativeButton(
            "No"
        ) { dialog, id -> dialog.cancel() }
        val alert11 = builder1.create()
        alert11.show()
    }
}