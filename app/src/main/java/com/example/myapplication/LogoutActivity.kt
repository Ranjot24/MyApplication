package com.example.myapplication

// LoginActivity.kt
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class LogoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        AuthManager.logout { success ->
            if (success) {
                // Navigate to the login screen on successful logout
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                // Handle logout failure
                Toast.makeText(this, "Logout failed. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}