package com.example.myapplication

/// CartViewModel.kt
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val cartRepository: CartRepository
    val allItems: LiveData<List<CartItem>>

    init {
        val cartItemDao = AppDatabase.getDatabase(application).cartItemDao()
        cartRepository = CartRepository(cartItemDao)
        allItems = cartRepository.allItems
    }

    fun getCartItemByProductId(productId: String): LiveData<CartItem?> {
            // Assuming repository has a method to get a cart item by product ID
            return cartRepository.getCartItemByProductId(productId)
    }

    fun insert(cartItem: CartItem) = viewModelScope.launch(Dispatchers.IO) {
        cartRepository.insert(cartItem)
    }

    fun updateCartItemQuantity(cartItem: CartItem, newQuantity: Int) = viewModelScope.launch(Dispatchers.IO) {
        cartRepository.updateCartItemQuantity(cartItem.productId, newQuantity)
    }

    fun deleteByProductId(productId: String) = viewModelScope.launch(Dispatchers.IO) {
        cartRepository.deleteByProductId(productId)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        cartRepository.deleteAll()
    }
}
