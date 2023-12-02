package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityOrderSummaryBinding

class OrderSummaryActivity : AppCompatActivity() {

    private lateinit var orderViewModel: OrderViewModel
    private lateinit var ordersAdapter: OrderAdapter
    private lateinit var binding: ActivityOrderSummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)
        binding = ActivityOrderSummaryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Retrieve the passed address from the intent
        val address = intent.getParcelableExtra<Address>("ADDRESS")
        val orderId = intent.getStringExtra("orderId")
        val orderTotal = intent.getDoubleExtra("orderTotal", 0.0)

        binding.tvOrderId.text = "Order ID: $orderId"
        binding.tvOrderTotal.text = "Order Total: $$orderTotal"

        // You can implement additional logic to handle order confirmation, etc.
        binding.btnConfirmOrder.setOnClickListener {
            // Handle order confirmation logic
            Toast.makeText(this, "Order confirmed!", Toast.LENGTH_SHORT).show()
            finish()
        }

        // Display the address details in your UI components
        displayAddressDetails(address)

        // Initialize the OrderViewModel
        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        // Call the fetchOrders or fetchOrderDetails functions as needed
        orderViewModel.fetchOrders()
        // or
        orderViewModel.fetchOrderDetails("your_order_id")

        // Observe the orders LiveData
        orderViewModel.orders.observe(this, Observer { orders ->
            // Handle the updated list of orders
            // This block will be executed whenever the orders LiveData is updated
            ordersAdapter.submitList(orders)
        })

        // Observe the orderDetails LiveData
        orderViewModel.orderDetails.observe(this, Observer { orderDetails ->
            // Handle the updated order details
            // This block will be executed whenever the orderDetails LiveData is updated
            binding.tvOrderDetails.text = orderDetails.orderId
        })
    }


    private fun displayAddressDetails(address: Address?) {
        if (address != null) {
            // Update your UI components with address details
            binding.textViewStreet.text = address.street
            binding.textViewCity.text = address.city
            binding.textViewState.text = address.state
            binding.textViewZipCode.text = address.zipCode
            // Update other TextViews with address details accordingly
        }
    }
}

