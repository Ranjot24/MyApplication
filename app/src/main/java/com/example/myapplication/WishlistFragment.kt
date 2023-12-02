package com.example.myapplication

import kotlinx.coroutines.launch
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentWishlistBinding

class WishlistFragment : Fragment() {

    private lateinit var binding: FragmentWishlistBinding
    private lateinit var wishlistViewModel: WishlistViewModel
    private lateinit var wishlistAdapter: WishlistAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        wishlistViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(WishlistViewModel::class.java)

        // Initialize RecyclerView and Adapter
        wishlistAdapter = WishlistAdapter { clickedWishlistItem ->
            val message = "Clicked on item: ${clickedWishlistItem.productName}"
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        binding.recyclerViewWishlist.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewWishlist.adapter = wishlistAdapter

        // Observe changes in the wishlist items
        wishlistViewModel.allItems.observe(viewLifecycleOwner, Observer { wishlistItems ->
            wishlistAdapter.submitList(wishlistItems)
            updateTotalPrice(wishlistItems)
        })

        // Other UI setup or event handling as needed
        binding.addButton.setOnClickListener {
            val productId = ""
            val productName = "Your Product Name"
            val price = 19.99 // Replace with the actual price
            val quantity = 1 // Replace with the actual quantity

            val wishlistItem = WishlistItem(
                productName = productName,
                price = price,
                quantity = quantity,
                productId = productId)
            wishlistViewModel.insert(wishlistItem)
        }

        binding.updateButton.setOnClickListener {
            val productIdToUpdate = "your_product_id" // Replace with the actual product ID

            // Assume you have a method to get the existing cart item by product ID
            val existingWishlistItemLiveData = wishlistViewModel.getWishlistItemByProductId(productIdToUpdate)

            existingWishlistItemLiveData.observe(viewLifecycleOwner, Observer { existingWishlistItem ->
                // Remove the observer to avoid multiple calls
                existingWishlistItemLiveData.removeObservers(viewLifecycleOwner)

                if (existingWishlistItem != null) {
                    // Modify the existing cart item with updated details
                    showUpdateDialog(existingWishlistItem, 2) // Replace with the updated quantity

                    // Update the cart item in the ViewModel
                    wishlistViewModel.updateWishlistItemQuantity(existingWishlistItem, 2)
                } else {
                    // Handle the case where the item with the specified product ID is not found
                    Toast.makeText(requireContext(), "Item not found in the cart", Toast.LENGTH_SHORT).show()
                }
            })
        }


        // Example: Trigger delete by product ID when a button is clicked
        binding.deleteButton.setOnClickListener {
            val productIdToDelete = "your_product_id" // Replace with the actual product ID
            wishlistViewModel.deleteByProductId(productIdToDelete)
        }

        // Example: Trigger delete all when a button is clicked
        binding.deleteAllButton.setOnClickListener {
            wishlistViewModel.deleteAll()
        }

        val sampleWishlistItem = WishlistItem(
            productName = "Sample Product",
            price = 9.99,
            quantity = 1,
            productId = "sample_product_id"
        )

        onQuantityChanged(wishlistItem = sampleWishlistItem, newQuantity = 2)
        onRemoveItemClicked(wishlistItem = sampleWishlistItem)

        // Other UI setup or event handling as needed
    }

    private fun showUpdateDialog(wishlistItem: WishlistItem, newQuantity: Int) {
        val dialog = WishlistUpdateDialog(requireContext()) { quantity ->
            // Handle quantity update
            wishlistItem.quantity = quantity
            wishlistViewModel.updateWishlistItemQuantity(wishlistItem, newQuantity)
        }
        dialog.show()
    }


    private fun updateTotalPrice(wishlistItems: List<WishlistItem>) {
        // Calculate and display the total price based on cart items
        val totalPrice = wishlistItems.sumByDouble { it.price * it.quantity }
        binding.tvTotalPrice.text = "Total Price: $$totalPrice"
    }

    private fun onQuantityChanged(wishlistItem: WishlistItem, newQuantity: Int) {
        // Your implementation for quantity change
        wishlistViewModel.updateWishlistItemQuantity(wishlistItem, newQuantity)

        // Optionally, you can update the UI to reflect the change in the cart
        updateTotalPrice(wishlistViewModel.allItems.value ?: emptyList())
    }


    private fun onRemoveItemClicked(wishlistItem: WishlistItem) {
        // Your implementation for item removal
        wishlistViewModel.deleteByProductId(wishlistItem.productId)
    }
}
