package com.example.stockmarket;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Button logoutButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        logoutButton = findViewById(R.id.logout_button);
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the stored user data
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear(); // Clear all preferences
                editor.apply();

                // Redirect to MainActivity (login page)
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close the SettingsActivity
                Toast.makeText(SettingsActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
