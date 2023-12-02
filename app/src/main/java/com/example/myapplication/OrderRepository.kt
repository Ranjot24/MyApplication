package com.example.myapplication

import retrofit2.Call

class OrderRepository(private val orderService: OrderService) {

    fun getOrderDetails(orderId: String): Call<Order> {
        return orderService.getOrderDetails(orderId)
    }

    fun getOrders(): Call<List<Order>> {
        return orderService.getOrders()
    }
}
