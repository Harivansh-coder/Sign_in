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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {

    // Firebase Auth

    private FirebaseAuth fauth;


    // Members declaration
    private Button login;

    private EditText email;
    private EditText password;

    private TextView forgot_pass;
    private TextView signup;

    private String strEmail;
    private String strPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);


        // initialized firebaseauth object

        fauth = FirebaseAuth.getInstance();

        // Binding the member variables with views
        login = (Button) findViewById(R.id.send_fps);

        email = (EditText) findViewById(R.id.email_ls);
        password = (EditText) findViewById(R.id.pass_ls);

        forgot_pass = (TextView) findViewById(R.id.forgot_ls);
        signup = (TextView) findViewById(R.id.signup);




        // login Button implementation
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting the string data from the editviews

                strEmail = email.getText().toString().trim();
                strPass = password.getText().toString().trim();


                if (strPass.length() != 0 && strEmail.length() != 0){

                    // signing in using email and password provided
                    fauth.signInWithEmailAndPassword(strEmail,strPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            // if email and password is valid
                            if (task.isSuccessful()){
                                Intent intent = new Intent(LoginScreen.this,DashBoard.class);
                                startActivity(intent);
                                finish();

                            }else Toast.makeText(LoginScreen.this,"Error invalid data",Toast.LENGTH_LONG).show();
                        }
                    });
                }else Toast.makeText(LoginScreen.this,"Error either of the field cannot be empty",Toast.LENGTH_LONG).show();

            }
        });


        // forgot password implementation
        forgot_pass.setOnClickListener(new View.OnClickListener() {
            String reset_email;
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginScreen.this,ForgotPassword.class);
                startActivity(intent);
                finish();


            }
        });


        // Signup implementation

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this,SignupScreen.class);
                startActivity(intent);
                finish();

            }
        });




    }


}