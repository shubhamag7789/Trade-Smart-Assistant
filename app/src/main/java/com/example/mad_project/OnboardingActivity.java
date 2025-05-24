package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class OnboardingActivity extends AppCompatActivity {
    private Button getStartedButton;
    private CardView featureCard1;
    private CardView featureCard2;
    private CardView featureCard3;
    private ImageView logoImage;
    private TextView appTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        // Initialize views
        getStartedButton = findViewById(R.id.getStartedButton);
        featureCard1 = findViewById(R.id.featureCard1);
        featureCard2 = findViewById(R.id.featureCard2);
        featureCard3 = findViewById(R.id.featureCard3);
        logoImage = findViewById(R.id.logoImage);
        appTitle = findViewById(R.id.appTitle);

        // Set initial states
        logoImage.setAlpha(0f);
        appTitle.setAlpha(0f);
        featureCard1.setAlpha(0f);
        featureCard2.setAlpha(0f);
        featureCard3.setAlpha(0f);
        getStartedButton.setAlpha(0f);

        // Start animations after layout is ready
        getWindow().getDecorView().post(() -> {
            // Animate logo and title
            logoImage.animate()
                    .alpha(1f)
                    .setDuration(500)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .withEndAction(() -> {
                        // Animate title
                        appTitle.animate()
                                .alpha(1f)
                                .setDuration(500)
                                .setInterpolator(new AccelerateDecelerateInterpolator())
                                .withEndAction(this::animateFeatureCards)
                                .start();
                    })
                    .start();
        });

        // Handle Get Started button click with animation
        getStartedButton.setOnClickListener(v -> {
            getStartedButton.animate()
                    .scaleX(0.95f)
                    .scaleY(0.95f)
                    .setDuration(100)
                    .withEndAction(() -> {
                        getStartedButton.animate()
                                .scaleX(1f)
                                .scaleY(1f)
                                .setDuration(100)
                                .withEndAction(this::navigateToLogin)
                                .start();
                    })
                    .start();
        });
    }

    private void animateFeatureCards() {
        // Animate first card
        featureCard1.animate()
                .alpha(1f)
                .translationY(0)
                .setDuration(500)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .withEndAction(() -> {
                    // Animate second card
                    featureCard2.animate()
                            .alpha(1f)
                            .translationY(0)
                            .setDuration(500)
                            .setInterpolator(new AccelerateDecelerateInterpolator())
                            .withEndAction(() -> {
                                // Animate third card
                                featureCard3.animate()
                                        .alpha(1f)
                                        .translationY(0)
                                        .setDuration(500)
                                        .setInterpolator(new AccelerateDecelerateInterpolator())
                                        .withEndAction(() -> {
                                            // Animate get started button
                                            getStartedButton.animate()
                                                    .alpha(1f)
                                                    .setDuration(300)
                                                    .setInterpolator(new AccelerateDecelerateInterpolator())
                                                    .start();
                                        })
                                        .start();
                            })
                            .start();
                })
                .start();
    }

    private void navigateToLogin() {
        startActivity(new Intent(OnboardingActivity.this, LoginActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }
} 