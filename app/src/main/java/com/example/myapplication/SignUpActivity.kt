package com.example.myapplication

// SignUpActivity.kt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            AuthManager.signUp(email, password) { success ->
                if (success) {
                    Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show()
                    // Navigate to the login screen on successful sign-up
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    // Handle sign-up failure
                    Toast.makeText(this, "Sign-up failed. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

