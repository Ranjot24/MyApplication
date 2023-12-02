package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        // Assuming you have a RecyclerView in your layout with id 'recyclerViewProducts'
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewProductList)

        // Assuming productList is a list of Product objects
        val productList: List<Product> = listOf(
            Product("Product 1", "Description 1", "", 0.5, ""),
            Product("Product 2", "Description 2", "", 0.6, ""),
            // Add more products as needed
        )

// Create an instance of the ProductAdapter
        val productAdapter = ProductAdapter(productList)

// Set the adapter to the RecyclerView
        recyclerView.adapter = productAdapter

// Set a LinearLayoutManager to the RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}