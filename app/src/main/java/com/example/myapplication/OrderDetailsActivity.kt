package com.example.myapplication

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityOrderDetailsBinding
import java.text.SimpleDateFormat
import java.util.*

class OrderDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve order details from intent or any other source
        val order: Order? = intent.getParcelableExtra("ORDER_KEY")

        if (order != null) {
            displayOrderDetails(order)
        } else {
            // Handle the case when order details are not available
            binding.tvOrderId.text = "Order details not available"
        }
    }

    private fun displayOrderDetails(order: Order) {
        // Display order details in UI components
        binding.tvOrderId.text = "Order ID: ${order.orderId}"
        binding.tvOrderDate.text = "Order Date: ${formatDate(order.date)}"
        binding.tvTotalPrice.text = "Order Total: $${order.totalPrice}"
        binding.tvTax.text = "Order Amount: ${order.tax}"
        binding.tvShippingAddress.text = "Order Amount: ${order.shippingAddress}"
        binding.tvOrderNumber.text = "Order Amount: ${order.orderNumber}"

        // You can continue displaying other details based on your Order model
    }

    private fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        return dateFormat.format(date)
    }
}

