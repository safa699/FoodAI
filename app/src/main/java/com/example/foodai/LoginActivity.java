package com.example.foodai;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText nameInput, emailInput, passwordInput;
    TextView titleText, subtitleText, switchModeText;
    Button actionButton;

    boolean isSignUp = false;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        titleText = findViewById(R.id.titleText);
        subtitleText = findViewById(R.id.subtitleText);
        switchModeText = findViewById(R.id.switchModeText);
        actionButton = findViewById(R.id.actionButton);

        preferences = getSharedPreferences(
                "FoodAIPreferences", MODE_PRIVATE);

        actionButton.setOnClickListener(v -> {
            if (isSignUp) {
                signUp();
            } else {
                login();
            }
        });

        switchModeText.setOnClickListener(v -> {
            isSignUp = !isSignUp;
            updateScreen();
        });
    }

    private void updateScreen() {
        if (isSignUp) {
            titleText.setText("Create Account");
            subtitleText.setText(
                    "Join FoodAI and start your healthy journey.");
            actionButton.setText("Sign Up");
            switchModeText.setText(
                    "Already have an account? Login");
            nameInput.setVisibility(View.VISIBLE);
        } else {
            titleText.setText("Welcome Back!");
            subtitleText.setText(
                    "Login to continue your healthy journey.");
            actionButton.setText("Login");
            switchModeText.setText(
                    "Don't have an account? Sign Up");
            nameInput.setVisibility(View.GONE);
        }
    }

    private void signUp() {
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString()
                .trim().toLowerCase();
        String password = passwordInput.getText().toString();

        if (name.isEmpty()) {
            nameInput.setError("Enter your name");
            nameInput.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Enter a valid email");
            emailInput.requestFocus();
            return;
        }

        if (password.length() < 6) {
            passwordInput.setError(
                    "Password must be at least 6 characters");
            passwordInput.requestFocus();
            return;
        }

        // Demo account storage for this device only
        preferences.edit()
                .putString("name", name)
                .putString("email", email)
                .putString("password", password)
                .apply();

        Toast.makeText(this,
                "Account created successfully!",
                Toast.LENGTH_SHORT).show();

        openMainScreen();
    }

    private void login() {
        String email = emailInput.getText().toString()
                .trim().toLowerCase();
        String password = passwordInput.getText().toString();

        if (email.isEmpty()) {
            emailInput.setError("Enter your email");
            emailInput.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordInput.setError("Enter your password");
            passwordInput.requestFocus();
            return;
        }

        String savedEmail = preferences.getString("email", "");
        String savedPassword =
                preferences.getString("password", "");

        if (email.equals(savedEmail)
                && password.equals(savedPassword)
                && !savedEmail.isEmpty()) {

            Toast.makeText(this,
                    "Login successful!",
                    Toast.LENGTH_SHORT).show();

            openMainScreen();

        } else {
            Toast.makeText(this,
                    "Account not found or password incorrect. Sign Up first.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void openMainScreen() {
        Intent intent = new Intent(
                LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
