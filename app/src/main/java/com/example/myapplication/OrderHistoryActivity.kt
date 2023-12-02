package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityOrderHistoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderHistoryBinding
    private lateinit var orderService: OrderService // Assume you have an OrderService instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize your OrderService (Retrofit instance) here
        orderService = OrderApiClient.create()

        setupRecyclerView()

        // Fetch orders from the backend
        fetchOrders()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewOrders.layoutManager = LinearLayoutManager(this)
        // Create an instance of your OrderAdapter and set it on the RecyclerView
        val orderAdapter = OrderAdapter()
        binding.recyclerViewOrders.adapter = orderAdapter
    }

    private fun fetchOrders() {
        // Make a network call to get a list of orders
        orderService.getOrders().enqueue(object : Callback<List<Order>> {
            override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
                if (response.isSuccessful) {
                    val orders = response.body()
                    // Update the RecyclerView with the list of orders
                    updateRecyclerView(orders)
                } else {
                    // Handle error
                    val errorMessage = "Error: ${response.code()}"
                    // You can log the error or show a toast/snackbar to the user
                    Log.e("Order API", errorMessage)
                    // Display an error message to the user using Toast or Snackbar
                    // Example using Toast:
                    Toast.makeText(this@OrderHistoryActivity, errorMessage, Toast.LENGTH_SHORT).show()
                    // Display an error message or take appropriate action
                }
            }

            override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                // Handle failure
                val errorMessage = "Failed to fetch orders. Please try again later."
                Toast.makeText(this@OrderHistoryActivity, errorMessage, Toast.LENGTH_SHORT).show()
                // You might want to log the error for debugging purposes
                Log.e("OrderHistoryActivity", "Failed to fetch orders", t)
            }
        })
    }

    private fun updateRecyclerView(orders: List<Order>?) {
        val orderAdapter = binding.recyclerViewOrders.adapter as? OrderAdapter
        orderAdapter?.submitList(orders)
    }
}


