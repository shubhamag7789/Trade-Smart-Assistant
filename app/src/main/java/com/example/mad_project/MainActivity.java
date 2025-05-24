package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Always start with onboarding
        startActivity(new Intent(MainActivity.this, OnboardingActivity.class));
        finish(); // Close MainActivity as we don't need it anymore
    }
}