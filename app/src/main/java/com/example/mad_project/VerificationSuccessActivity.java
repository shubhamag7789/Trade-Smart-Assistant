package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class VerificationSuccessActivity extends AppCompatActivity {
    private MaterialButton dashboardButton;
    private MaterialButton homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_success);

        dashboardButton = findViewById(R.id.dashboardButton);
        homeButton = findViewById(R.id.homeButton);

        // Handle Continue to Dashboard button click
        dashboardButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, DashboardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finishAffinity();
        });

        // Handle Back to Home button click
        homeButton.setOnClickListener(v -> {
            // Go back to MainActivity which will show onboarding
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finishAffinity();
        });
    }
} 