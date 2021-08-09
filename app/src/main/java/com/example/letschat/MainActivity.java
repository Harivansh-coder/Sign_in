package com.example.letschat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Thread for loadscreen
        Thread LoadScreen = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(2000);  //Delay of 3 seconds
                } catch (Exception e) {

                } finally {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null){
                        Intent intent = new Intent(MainActivity.this, DashBoard.class); // need work here
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent = new Intent(MainActivity.this, LoginScreen.class);
                        startActivity(intent);
                        finish();
                    }


                }
            }
        };
        LoadScreen.start();
    }
}