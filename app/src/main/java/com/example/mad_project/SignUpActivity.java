package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private TextInputLayout emailLayout, passwordLayout;
    private EditText emailEditText, passwordEditText;
    private ImageView passwordToggle;
    private Button signUpButton;
    private TextView loginText;
    private boolean passwordVisible = false;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        emailLayout = findViewById(R.id.emailLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordToggle = findViewById(R.id.passwordToggle);
        signUpButton = findViewById(R.id.signUpButton);
        loginText = findViewById(R.id.loginText);

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

        signUpButton.setOnClickListener(v -> {
            if (validateInputs()) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, DashboardActivity.class));
                                finish();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Sign Up failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

        loginText.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            finish();
        });
    }

    private boolean validateInputs() {
        boolean isValid = true;
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            emailLayout.setError("Email is required");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError("Please enter a valid email address");
            isValid = false;
        } else {
            emailLayout.setError(null);
        }

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
