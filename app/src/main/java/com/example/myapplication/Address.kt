package com.example.myapplication

// Address.kt
import android.os.Parcel
import android.os.Parcelable

data class Address(
    val street: String,
    val city: String,
    val state: String,
    val zipCode: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )
    fun isValid(): Boolean {
        // Check if any field is empty
        if (street.isEmpty() || city.isEmpty() || state.isEmpty() || zipCode.isEmpty()) {
            return false
        }
        return true
    }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(street)
        parcel.writeString(city)
        parcel.writeString(state)
        parcel.writeString(zipCode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Address> {
        override fun createFromParcel(parcel: Parcel): Address {
            return Address(parcel)
        }

        override fun newArray(size: Int): Array<Address?> {
            return arrayOfNulls(size)
        }
    }
}

