package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout emailLayout;
    private TextInputLayout passwordLayout;
    private EditText emailEditText;
    private EditText passwordEditText;
    private ImageView passwordToggle;
    private Button signInButton;
    private TextView signUpText;
    private TextView forgotPasswordText;
    private boolean passwordVisible = false;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // If user is already logged in, go to Dashboard
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish();
        }

        // Initialize views
        emailLayout = findViewById(R.id.emailLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordToggle = findViewById(R.id.passwordToggle);
        signInButton = findViewById(R.id.signInButton);
        signUpText = findViewById(R.id.signUpText);
        forgotPasswordText = findViewById(R.id.forgotPasswordText);

        // Password visibility toggle
        passwordToggle.setOnClickListener(v -> {
            passwordVisible = !passwordVisible;
            if (passwordVisible) {
                passwordEditText.setTransformationMethod(null);
                passwordToggle.setImageResource(R.drawable.ic_eye_off);
            } else {
                passwordEditText.setTransformationMethod(new PasswordTransformationMethod());
                passwordToggle.setImageResource(R.drawable.ic_eye);
            }
            passwordEditText.setSelection(passwordEditText.getText().length());
        });

        // Sign In button click
        signInButton.setOnClickListener(v -> {
            if (validateInputs()) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                                finish(); // Close login activity
                            } else {
                                Toast.makeText(LoginActivity.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

        // Sign Up text click
        signUpText.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        });

        // Forgot Password text click
        forgotPasswordText.setOnClickListener(v -> {
            Toast.makeText(LoginActivity.this, "Forgot password functionality coming soon!", Toast.LENGTH_SHORT).show();
        });
    }

    private boolean validateInputs() {
        boolean isValid = true;
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validate email
        if (TextUtils.isEmpty(email)) {
            emailLayout.setError("Email is required");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError("Please enter a valid email address");
            isValid = false;
        } else {
            emailLayout.setError(null);
        }

        // Validate password
        if (TextUtils.isEmpty(password)) {
            passwordLayout.setError("Password is required");
            isValid = false;
        } else if (password.length() < 6) {
            passwordLayout.setError("Password must be at least 6 characters");
            isValid = false;
        } else {
            passwordLayout.setError(null);
        }

        return isValid;
    }
}
