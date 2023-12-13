package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WishlistItemDao {

    @Query("SELECT * FROM wishlist_table")
    fun getAllItems(): LiveData<List<WishlistItem>>

    @Query("UPDATE wishlist_table SET quantity = :newQuantity WHERE productId = :productId")
    suspend fun updateWishlistItemQuantity(productId: String, newQuantity: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishlistItem(wishlistItem: WishlistItem)

    @Query("DELETE FROM wishlist_table WHERE productId = :productId")
    suspend fun deleteWishlistItemByProductId(productId: String)

    @Query("SELECT * FROM wishlist_table WHERE productId = :productId")
    suspend fun getWishlistItemByProductId(productId: String)

    @Query("DELETE FROM wishlist_table")
    suspend fun deleteAllWishlistItems()

    companion object {
        fun getWishlistItemByProductId(productId: String): LiveData<WishlistItem?> {
            return getWishlistItemByProductId(productId)
        }
    }
}

