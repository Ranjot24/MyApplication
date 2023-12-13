package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.lifecycle.ViewModelProvider
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

        wishlistViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(WishlistViewModel::class.java)

        wishlistAdapter = WishlistAdapter { clickedWishlistItem ->
            val message = "Clicked on item: ${clickedWishlistItem.productName}"
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        binding.recyclerViewWishlist.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewWishlist.adapter = wishlistAdapter

        wishlistViewModel.allItems.observe(viewLifecycleOwner, Observer { wishlistItems ->
            wishlistAdapter.submitList(wishlistItems)
            updateTotalPrice(wishlistItems)
        })

        binding.addButton.setOnClickListener {
            val productId = "your_product_id"
            val productName = "Your Product Name"
            val price = 19.99 // Replace with the actual price
            val quantity = 1 // Replace with the actual quantity

            val wishlistItem = WishlistItem(
                productName = productName,
                price = price,
                quantity = quantity,
                productId = productId
            )
            wishlistViewModel.insert(wishlistItem)
        }

        binding.updateButton.setOnClickListener {
            val productIdToUpdate = "your_product_id"

            wishlistViewModel.getWishlistItemByProductId(productIdToUpdate)
                .observe(viewLifecycleOwner) { existingWishlistItem ->
                    existingWishlistItem?.let {
                        showUpdateDialog(existingWishlistItem, 2)
                        wishlistViewModel.updateWishlistItemQuantity(existingWishlistItem, 2)
                    } ?: run {
                        Toast.makeText(
                            requireContext(),
                            "Item not found in the wishlist",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }


        binding.deleteButton.setOnClickListener {
            val productIdToDelete = "your_product_id"
            wishlistViewModel.deleteByProductId(productIdToDelete)
        }

        binding.deleteAllButton.setOnClickListener {
            wishlistViewModel.deleteAll()
        }

        val sampleWishlistItem = WishlistItem(
            productName = "Sample Product",
            price = 9.99,
            quantity = 1,
            productId = "sample_product_id"
        )

        onQuantityChanged(sampleWishlistItem, 2)
        onRemoveItemClicked(sampleWishlistItem)
    }

    private fun showUpdateDialog(wishlistItem: WishlistItem, newQuantity: Int) {
        val dialog = WishlistUpdateDialog(requireContext()) { quantity ->
            wishlistItem.quantity = quantity
            wishlistViewModel.updateWishlistItemQuantity(wishlistItem, newQuantity)
        }
        dialog.show()
    }

    private fun updateTotalPrice(wishlistItems: List<WishlistItem>) {
        val totalPrice = wishlistItems.sumByDouble { it.price * it.quantity }
        binding.tvTotalPrice.text = "Total Price: $$totalPrice"
    }

    private fun onQuantityChanged(wishlistItem: WishlistItem, newQuantity: Int) {
        wishlistViewModel.updateWishlistItemQuantity(wishlistItem, newQuantity)
        updateTotalPrice(wishlistViewModel.allItems.value ?: emptyList())
    }

    private fun onRemoveItemClicked(wishlistItem: WishlistItem) {
        wishlistViewModel.deleteByProductId(wishlistItem.productId)
    }
}