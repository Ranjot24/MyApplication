package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Feedback
import com.google.android.play.core.integrity.e
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class FeedbackActivity : AppCompatActivity() {

    private lateinit var userId: String // You need to set the user ID appropriately

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        val etComment = findViewById<EditText>(R.id.etFeedbackComment)
        val btnSubmitFeedback = findViewById<Button>(R.id.btnSubmitFeedback)

        btnSubmitFeedback.setOnClickListener {
            val comment = etComment.text.toString().trim()

            if (comment.isNotEmpty()) {
                // Assuming you have the product ID, replace "your_product_id" with the actual ID
                val productId = "your_product_id"

                val feedback = Feedback(
                    userId = userId,
                    productId = productId,
                    comment = comment,
                    timestamp = System.currentTimeMillis()
                )

                saveFeedbackToFirestore(feedback)
            } else {
                // Handle empty comment
                // You can show a toast or set an error on the EditText
                Toast.makeText(this, "Comment failed. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveFeedbackToFirestore(feedback: Feedback) {
        val feedbackCollection = FirebaseFirestore.getInstance().collection("feedback")

        feedbackCollection.add(feedback)
            .addOnSuccessListener {
                // Feedback saved successfully
                // You can show a success message or navigate to another screen
                finish()
            }
                val errorMessage = "Failed to save feedback. Please try again."
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                // You can show an error message or log the exception
    }
}
