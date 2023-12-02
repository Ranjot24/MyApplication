package com.example.myapplication

// OrderAdapter.kt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Order
import com.example.myapplication.databinding.ItemOrderBinding

class OrderAdapter : ListAdapter<Order, OrderAdapter.OrderViewHolder>(OrderDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = getItem(position)
        holder.bind(order)
    }

    inner class OrderViewHolder(private val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(order: Order) {
            // Bind your order data to the view here
            binding.orderIdTextView.text = "Order ID: ${order.orderId}"
            binding.orderDateTextView.text = "Total Price: $${order.date}"
            binding.orderTotalPriceTextView.text = "Order Amount: ${order.totalPrice}"
            binding.taxTextView.text = "Order Amount: ${order.tax}"
            binding.shippingAddressTextView.text = "Order Amount: ${order.shippingAddress}"
            binding.orderNumberTextView.text = "Order Amount: ${order.orderNumber}"
            // ... other bindings
        }
    }

    private class OrderDiffCallback : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.orderId == newItem.orderId
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            // Compare other fields if needed
            return oldItem == newItem
        }
    }
}

