package com.example.myapplication

import androidx.lifecycle.LiveData
import com.example.myapplication.WishlistItemDao
import com.example.myapplication.WishlistItem

class WishlistRepository(private val wishlistItemDao: WishlistItemDao) {

    val allItems: LiveData<List<WishlistItem>> = wishlistItemDao.getAllItems()

    suspend fun getWishlistItemByProductId(productId: String) {
        return wishlistItemDao.getWishlistItemByProductId(productId)
    }

    suspend fun insert(wishlistItem: WishlistItem) {
        wishlistItemDao.insertWishlistItem(wishlistItem)
    }

    suspend fun deleteByProductId(productId: String) {
        wishlistItemDao.deleteWishlistItemByProductId(productId)
    }

    suspend fun deleteAll() {
        wishlistItemDao.deleteAllWishlistItems()
    }

    suspend fun updateWishlistItemQuantity(productId: String, newQuantity: Int) {
        wishlistItemDao.updateWishlistItemQuantity(productId, newQuantity)
    }
}


