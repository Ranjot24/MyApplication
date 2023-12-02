package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface OrderService {

    // Define endpoint for getting order details by order ID
    @GET("orders/{orderId}")
    fun getOrderDetails(@Path("orderId") orderId: String): Call<Order>

    // Define endpoint for getting a list of orders
    @GET("orders")
    fun getOrders(): Call<List<Order>>
}

