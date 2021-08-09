package com.example.letschat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class DashBoard extends AppCompatActivity {

    private FirebaseAuth fauth;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        fauth = FirebaseAuth.getInstance();

        // Linking with the UI

        logout = (Button) findViewById(R.id.logout);

        // Button implementation

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fauth.signOut();
                Toast.makeText(DashBoard.this,"Logged out successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DashBoard.this, LoginScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }
}