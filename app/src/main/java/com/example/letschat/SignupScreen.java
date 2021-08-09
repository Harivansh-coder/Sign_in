package com.example.letschat;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupScreen extends AppCompatActivity {

    private FirebaseAuth fauth;

    // member variables created

    private Button cancel;
    private Button sign_up;

    private EditText name;
    private EditText email;
    private EditText pass;
    private EditText con_pass;

    private String str_name;
    private String str_email;
    private String str_pass;
    private String str_conpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        fauth = FirebaseAuth.getInstance();

        // linking with UI
        name = (EditText) findViewById(R.id.name_sus);
        email = (EditText) findViewById(R.id.email_sus);
        pass = (EditText) findViewById(R.id.pass_sus);
        con_pass = (EditText) findViewById(R.id.con_pass_sus);

        cancel = (Button) findViewById(R.id.cancel_but);
        sign_up = (Button) findViewById(R.id.signup_but);

        // button implementation
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupScreen.this, LoginScreen.class);
                startActivity(intent);
                finish();
            }
        });

        // signup button

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_name = name.getText().toString().trim();
                str_email = email.getText().toString().trim();
                str_pass = pass.getText().toString().trim();
                str_conpass = con_pass.getText().toString().trim();

                if (str_name.length() != 0 && str_email.length() != 0 && str_pass.length() != 0 && str_conpass.length() != 0){
                    if (str_pass.equals(str_conpass)){

                        fauth.createUserWithEmailAndPassword(str_email,str_conpass)
                                .addOnCompleteListener(SignupScreen.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful()) {

                                            Log.d(TAG, "createUserWithEmail:success");
                                            Toast.makeText(SignupScreen.this,"Account created successfully now login using that account",Toast.LENGTH_LONG).show();

                                            Intent intent = new Intent(SignupScreen.this,LoginScreen.class);
                                            startActivity(intent);
                                            finish();

                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(SignupScreen.this, "Password too short or email already used", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }else Toast.makeText(SignupScreen.this,"Both passwords must be same",Toast.LENGTH_LONG).show();

                }else Toast.makeText(SignupScreen.this,"Error either of the field cannot be empty",Toast.LENGTH_LONG).show();

            }
        });



    }


}

