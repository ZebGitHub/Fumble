package com.example.fumble;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmail;
    private EditText loginPassword;
    private Button buttonLogin;
    private TextView linkRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme); // Make sure AppTheme extends from Theme.AppCompat
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(this);


        // Initialize the views
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        linkRegister = findViewById(R.id.linkRegister);

        // Set up the button click listener for login
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Add actual login logic here
                String email = loginEmail.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {
                    // Perform your login logic here, e.g., authenticate with a server
                    // This is just a placeholder for the logic
                    performLogin(email, password);
                } else {
                    Toast.makeText(LoginActivity.this, "Please enter your email and password!", Toast.LENGTH_LONG).show();
                }
            }
        });


        // Set up the text view click listener for navigating to the registration screen
        linkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Registration Activity
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);

            }
        });
    }

    private void performLogin(String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI or navigate to another activity
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                        startActivity(intent);
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "Login failed!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
