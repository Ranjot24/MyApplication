package com.example.myapplication

// CartItemDao.kt
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CartItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: CartItem)

    @Query("UPDATE cart_item SET quantity = :newQuantity WHERE productId = :productId")
    suspend fun updateCartItemQuantity(productId: String, newQuantity: Int)

    @Query("DELETE FROM cart_item WHERE productId = :productId")
    suspend fun deleteByProductId(productId: String)

    @Query("DELETE FROM cart_item")
    suspend fun deleteAll()

    @Query("SELECT * FROM cart_item")
    fun getAllItems(): LiveData<List<CartItem>>

    @Query("SELECT * FROM cart_item WHERE productId = :productId")
    fun getItemById(productId: String): LiveData<CartItem?>

    @Query("SELECT * FROM cart_item WHERE productId = :productId")
    fun getCartItemByProductId(productId: String): LiveData<CartItem?>
}


