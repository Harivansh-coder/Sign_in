package com.example.letschat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class ForgotPassword extends AppCompatActivity {

    private FirebaseAuth fauth;

    private EditText email;

    private Button back;
    private Button send;

    private String str_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Binding with UI

        email = (EditText) findViewById(R.id.email_fps);


        back = (Button) findViewById(R.id.Back_fps);
        send = (Button) findViewById(R.id.send_fps);


        // Button implementation
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_email = email.getText().toString().trim();
                // sending the password reset email
                fauth.sendPasswordResetEmail(str_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPassword.this, "password reset email has been sent to your email", Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(ForgotPassword.this, "Invalid email", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        // Back Button

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this,LoginScreen.class);
                startActivity(intent);
                finish();
            }
        });


    }

    
}