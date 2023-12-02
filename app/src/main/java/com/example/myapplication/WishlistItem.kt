package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

// CartItem.kt
@Entity(tableName = "wishlist_table")
data class WishlistItem(
    @PrimaryKey val productName: String,
    val price: Double,
    var quantity: Int,
    val productId: String
)