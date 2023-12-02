package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    // Define your LiveData for home items
    private val _homeItems = MutableLiveData<List<YourItemType>>()
    val homeItems: LiveData<List<YourItemType>> get() = _homeItems

    // Add a function to update the home items
    fun updateHomeItems(newItems: List<YourItemType>) {
        _homeItems.value = newItems
    }
}


