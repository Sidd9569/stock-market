package com.example.stockmarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText securityAnswerEditText;
    private EditText newPasswordEditText;
    private Button changePasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Initialize views
        emailEditText = findViewById(R.id.emailEditText);
        securityAnswerEditText = findViewById(R.id.securityAnswerEditText);
        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        changePasswordButton = findViewById(R.id.changePasswordButton);

        // Set up the change password button listener
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get values from input fields
                String email = emailEditText.getText().toString().trim();
                String securityAnswer = securityAnswerEditText.getText().toString().trim();
                String newPassword = newPasswordEditText.getText().toString().trim();

                // Validate input
                if (email.isEmpty() || securityAnswer.isEmpty() || newPassword.isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO: Add logic to check the email and security answer with the database
                // Here, add your logic to validate the existing email and security answer
                // For demonstration purposes, we assume that the email and answer are correct
                boolean isEmailValid = true; // Replace with actual validation logic
                boolean isSecurityAnswerCorrect = true; // Replace with actual validation logic

                if (isEmailValid && isSecurityAnswerCorrect) {
                    // TODO: Add logic to update the password in the database

                    // Show confirmation message
                    Toast.makeText(ForgotPasswordActivity.this, "Password updated successfully!", Toast.LENGTH_SHORT).show();

                    // Redirect to MainActivity
                    Intent intent = new Intent(ForgotPasswordActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Invalid email or security answer.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
