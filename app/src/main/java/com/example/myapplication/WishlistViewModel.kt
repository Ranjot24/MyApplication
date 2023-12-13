package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WishlistViewModel(application: Application) : AndroidViewModel(application) {

    private val wishlistRepository: WishlistRepository

    val allItems: LiveData<List<WishlistItem>>

    init {
        val wishlistItemDao = AppDatabase.getDatabase(application).wishlistItemDao()
        wishlistRepository = WishlistRepository(wishlistItemDao)
        allItems = wishlistRepository.allItems
    }

    fun getWishlistItemByProductId(productId: String): LiveData<WishlistItem?> {
        return WishlistItemDao.getWishlistItemByProductId(productId)
    }

    fun updateWishlistItemQuantity(wishlistItem: WishlistItem, newQuantity: Int) = viewModelScope.launch(Dispatchers.IO) {
        wishlistRepository.updateWishlistItemQuantity(wishlistItem.productId, newQuantity)
    }

    fun insert(wishlistItem: WishlistItem) = viewModelScope.launch(Dispatchers.IO) {
        wishlistRepository.insert(wishlistItem)
    }

    fun deleteByProductId(productId: String) = viewModelScope.launch(Dispatchers.IO) {
        wishlistRepository.deleteByProductId(productId)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        wishlistRepository.deleteAll()
    }
}

