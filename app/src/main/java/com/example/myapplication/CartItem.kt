package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

// CartItem.kt
@Entity(tableName = "cart_item")
data class CartItem(
    @PrimaryKey val productId: String,
    val productName: String,
    var quantity: Int,
    val price: Double
)




