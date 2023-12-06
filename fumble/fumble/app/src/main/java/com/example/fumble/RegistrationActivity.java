package com.example.fumble;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;


public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        FirebaseApp.initializeApp(this);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


        // Initialize Views
        EditText registrationName = findViewById(R.id.registrationName);
        EditText registrationEmail = findViewById(R.id.registrationEmail);
        EditText registrationPassword = findViewById(R.id.registrationPassword);
        EditText confirmPasswordEditText = findViewById(R.id.confirmPassword);
        Button registerButton = findViewById(R.id.buttonRegister);
        TextView linkLogin = findViewById(R.id.linkLogin);

        // Handle registration logic with registerButton here...

        // Validate password complexity
        registerButton.setOnClickListener(view -> {
            String name = registrationName.getText().toString().trim();
            String email = registrationEmail.getText().toString().trim();
            String password = registrationPassword.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            if (!isValidPassword(password)) {
                registrationPassword.setError("Password must contain at least one capital letter.");
            } else if (!password.equals(confirmPassword)) {
                confirmPasswordEditText.setError("Passwords do not match.");
            } else {
                // Proceed with registration
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Registration success
                                // You can save additional user data to the database here if needed
                                // For simplicity, let's assume registration is complete once the user is authenticated
                                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish(); // Finish the current activity to prevent going back to it using the back button
                            } else {
                                // Registration failed
                                Toast.makeText(RegistrationActivity.this, "Registration failed. " + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // Navigate back to Login Page
        linkLogin.setOnClickListener(view -> {
            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private boolean isValidPassword(String password) {
        return password.matches(".*[A-Z].*");
    }
}