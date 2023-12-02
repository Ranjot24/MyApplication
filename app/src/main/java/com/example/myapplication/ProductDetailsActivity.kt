package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityProductDetailsBinding

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Retrieve product information from the intent
        val productId = intent.getStringExtra("PRODUCT_ID")
        val productName = intent.getStringExtra("PRODUCT_NAME")
        val productDescription = intent.getStringExtra("PRODUCT_DESCRIPTION")
        val productPrice = intent.getDoubleExtra("PRODUCT_PRICE", 0.0)
        val productImageUrl = intent.getStringExtra("PRODUCT_IMAGE_URL")

        // Set product details to the UI
        binding.tvProductName.text = productName
        binding.tvProductDescription.text = productDescription
        binding.tvProductPrice.text = productPrice.toString()

        // Load and display the product image using your preferred image loading library (e.g., Glide, Picasso)
        // Example with Glide:
        // Glide.with(this).load(productImageUrl).into(binding.imageViewProduct)

        Glide.with(this)
            .load(productImageUrl)
            .into(binding.ivProductImage)
        // You can add more logic or UI elements as needed
    }
    // Example: In response to some user action

}



