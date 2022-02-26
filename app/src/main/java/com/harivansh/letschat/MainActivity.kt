package com.harivansh.letschat

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private var fauth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // firebase instance
        fauth = FirebaseAuth.getInstance()

        // Thread for loadscreen
        val LoadScreen: Thread = object : Thread() {
            override fun run() {
                try {
                    super.run()
                    sleep(1000) //Delay of 1 seconds
                } catch (e: Exception) {
                    Log.d(ContentValues.TAG, e.message!!)
                } finally {
                    startActivity(Intent(this@MainActivity, Signin::class.java))
                    finish()
                    val user = fauth!!.currentUser
                    if (user != null) {
                        startActivity(Intent(this@MainActivity, DashBoard::class.java))
                        finish()
                    } else {
                        startActivity(Intent(this@MainActivity, Signin::class.java))
                        finish()
                    }
                }
            }
        }
        LoadScreen.start()
    }
}