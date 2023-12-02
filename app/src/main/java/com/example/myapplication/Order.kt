package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Order(
    val orderId: String,
    val date: Date,
    val products: List<Product>, // Assuming you have a Product class
    val totalPrice: Double,
    val tax: Double,
    val shippingAddress: String,
    val orderNumber: Long,
    var status: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        Date(parcel.readLong()),
        mutableListOf<Product>().apply {
            parcel.readList(this, Product::class.java.classLoader)
        },
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readString()!!
        // Add other properties as needed
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(orderId)
        parcel.writeLong(date.time)
        parcel.writeList(products)
        parcel.writeDouble(totalPrice)
        parcel.writeDouble(tax)
        parcel.writeString(shippingAddress)
        parcel.writeLong(orderNumber)
        // Write other properties as needed
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Order> {
        override fun createFromParcel(parcel: Parcel): Order {
            return Order(parcel)
        }

        override fun newArray(size: Int): Array<Order?> {
            return arrayOfNulls(size)
        }
    }
}

