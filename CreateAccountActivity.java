package com.example.stockmarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText securityQuestionEditText;
    private Button createAccountButton;

    private static final String EMPTY_FIELDS_MESSAGE = "Please fill in all fields.";
    private static final String PASSWORD_MISMATCH_MESSAGE = "Passwords do not match.";
    private static final String ACCOUNT_CREATION_SUCCESS_MESSAGE = "Account created successfully!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Initialize views
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        securityQuestionEditText = findViewById(R.id.securityQuestionEditText);
        createAccountButton = findViewById(R.id.createAccountButton);

        // Set up the create account button listener
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get values from input fields
                String name = nameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();
                String securityAnswer = securityQuestionEditText.getText().toString().trim();

                // Validate input
                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || securityAnswer.isEmpty()) {
                    Toast.makeText(CreateAccountActivity.this, EMPTY_FIELDS_MESSAGE, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(CreateAccountActivity.this, PASSWORD_MISMATCH_MESSAGE, Toast.LENGTH_SHORT).show();
                    return;
                }

                // TODO: Add database insertion logic here to save the account information

                // Show confirmation message
                Toast.makeText(CreateAccountActivity.this, ACCOUNT_CREATION_SUCCESS_MESSAGE, Toast.LENGTH_SHORT).show();

                // Return to MainActivity
                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

