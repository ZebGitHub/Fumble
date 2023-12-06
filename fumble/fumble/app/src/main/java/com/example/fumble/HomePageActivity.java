package com.example.fumble;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class HomePageActivity extends AppCompatActivity {

    private Button buttonPostMatch;
    private Button buttonViewMatches;
    private Button buttonSignOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        buttonPostMatch = findViewById(R.id.buttonPostMatch);
        buttonViewMatches = findViewById(R.id.buttonViewMatches);
        buttonSignOut = findViewById(R.id.buttonSignOut);


        buttonPostMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start PostMatchActivity
                Intent intent = new Intent(HomePageActivity.this, PostMatchActivity.class);
                startActivity(intent);
            }
        });

        buttonViewMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start ListOfMatchesActivity
                Intent intent = new Intent(HomePageActivity.this, ListOfMatchesActivity.class);
                startActivity(intent);
            }
        });

        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sign out from Firebase
                FirebaseAuth.getInstance().signOut();

                // Redirect to LoginActivity
                Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear the activity stack
                startActivity(intent);
                finish(); // Ensure this activity is closed
            }
        });
    }
    }

