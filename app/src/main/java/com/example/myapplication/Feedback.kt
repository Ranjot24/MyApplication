package com.example.myapplication

// Feedback.kt
data class Feedback(
    val userId: String,
    val productId: String,
    val comment: String,
    val timestamp: Long
)
