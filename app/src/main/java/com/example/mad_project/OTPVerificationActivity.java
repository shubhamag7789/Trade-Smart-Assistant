package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class OTPVerificationActivity extends AppCompatActivity {
    private EditText[] otpBoxes;
    private MaterialButton verifyButton;
    private TextView resendButton;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        // Get email from intent
        userEmail = getIntent().getStringExtra("email");

        // Initialize views
        initializeViews();
        setupOTPInputs();

        // Verify button click
        verifyButton.setOnClickListener(v -> {
            String otp = getOTPFromBoxes();
            if (otp.length() == 6) {
                verifyOTP(otp);
            } else {
                Toast.makeText(this, "Please enter complete OTP", Toast.LENGTH_SHORT).show();
            }
        });

        // Resend button click
        resendButton.setOnClickListener(v -> {
            resendOTP();
            Toast.makeText(this, "OTP resent to your email", Toast.LENGTH_SHORT).show();
        });
    }

    private void initializeViews() {
        otpBoxes = new EditText[6];
        otpBoxes[0] = findViewById(R.id.otpBox1);
        otpBoxes[1] = findViewById(R.id.otpBox2);
        otpBoxes[2] = findViewById(R.id.otpBox3);
        otpBoxes[3] = findViewById(R.id.otpBox4);
        otpBoxes[4] = findViewById(R.id.otpBox5);
        otpBoxes[5] = findViewById(R.id.otpBox6);
        verifyButton = findViewById(R.id.verifyButton);
        resendButton = findViewById(R.id.resendButton);
    }

    private void setupOTPInputs() {
        for (int i = 0; i < 6; i++) {
            final int currentIndex = i;
            otpBoxes[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() == 1 && currentIndex < 5) {
                        otpBoxes[currentIndex + 1].requestFocus();
                    } else if (s.length() == 0 && currentIndex > 0) {
                        otpBoxes[currentIndex - 1].requestFocus();
                    }
                }
            });
        }
    }

    private String getOTPFromBoxes() {
        StringBuilder otp = new StringBuilder();
        for (EditText box : otpBoxes) {
            otp.append(box.getText().toString());
        }
        return otp.toString();
    }

    private void verifyOTP(String otp) {
        // TODO: Implement your actual OTP verification logic here
        // For demo purposes, we'll consider any 6-digit OTP as valid
        startActivity(new Intent(this, VerificationSuccessActivity.class));
        finish(); // Close OTP verification screen
    }

    private void resendOTP() {
        // TODO: Implement your OTP resend logic here
        // This should generate a new OTP and send it to the user's email
        // For now, just clear the OTP boxes
        for (EditText box : otpBoxes) {
            box.setText("");
        }
        otpBoxes[0].requestFocus();
    }
} 