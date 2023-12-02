package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderViewModel(private val orderRepository: OrderRepository) : ViewModel() {

    private val _orders = MutableLiveData<List<Order>>()
    val orders: LiveData<List<Order>> get() = _orders

    private val _orderDetails = MutableLiveData<Order>()
    val orderDetails: LiveData<Order> get() = _orderDetails

    // Function to fetch orders
    fun fetchOrders() {
        orderRepository.getOrders().enqueue(object : Callback<List<Order>> {
            override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
                if (response.isSuccessful) {
                    _orders.value = response.body()
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                // Handle failure
            }
        })
    }

    fun fetchOrderDetails(orderId: String) {
        orderRepository.getOrderDetails(orderId).enqueue(object : Callback<Order> {
            override fun onResponse(call: Call<Order>, response: Response<Order>) {
                if (response.isSuccessful) {
                    _orderDetails.value = response.body()
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<Order>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
