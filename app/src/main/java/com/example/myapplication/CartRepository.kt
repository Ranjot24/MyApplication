package com.example.myapplication

// CartRepository.kt
import androidx.lifecycle.LiveData

class CartRepository(private val cartItemDao: CartItemDao) {

    val allItems: LiveData<List<CartItem>> = cartItemDao.getAllItems()

    fun getCartItemByProductId(productId: String): LiveData<CartItem?> {
        return cartItemDao.getCartItemByProductId(productId)
    }

    suspend fun insert(cartItem: CartItem) {
        cartItemDao.insert(cartItem)
    }

    suspend fun updateCartItemQuantity(productId: String, newQuantity: Int) {
        cartItemDao.updateCartItemQuantity(productId, newQuantity)
    }

    suspend fun deleteByProductId(productId: String) {
        cartItemDao.deleteByProductId(productId)
    }

    suspend fun deleteAll() {
        cartItemDao.deleteAll()
    }

    fun get(productId: String): LiveData<CartItem?> {
        return cartItemDao.getItemById(productId)
    }
}
