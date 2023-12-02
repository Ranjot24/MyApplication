package com.example.myapplication

// CartFragment.kt
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        cartViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(CartViewModel::class.java)

        // Initialize RecyclerView and Adapter
        cartAdapter = CartAdapter(
            onItemClicked = { clickedCartItem ->
                val message = "Clicked on item: ${clickedCartItem.productName}"
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        ) { cartItem, newQuantity ->
            showUpdateDialog(cartItem, 2)
        }

        binding.recyclerViewCart.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCart.adapter = cartAdapter

        // Observe changes in the cart items
        cartViewModel.allItems.observe(viewLifecycleOwner, Observer { cartItems ->
            cartAdapter.submitList(cartItems)
            updateTotalPrice(cartItems)
        })

        binding.addButton.setOnClickListener {
            val productId = ""
            val productName = "Your Product Name"
            val price = 19.99 // Replace with the actual price
            val quantity = 1 // Replace with the actual quantity

            val cartItem = CartItem(
                productName = productName,
                price = price,
                quantity = quantity,
                productId = productId)
            cartViewModel.insert(cartItem)
        }

        binding.updateButton.setOnClickListener {
            val productIdToUpdate = "your_product_id" // Replace with the actual product ID

            // Assume you have a method to get the existing cart item by product ID
            val existingCartItemLiveData = cartViewModel.getCartItemByProductId(productIdToUpdate)

            // Observe the LiveData
            existingCartItemLiveData.observe(viewLifecycleOwner) { existingCartItem ->
                // Remove the observer to avoid multiple calls
                existingCartItemLiveData.removeObservers(viewLifecycleOwner)

                if (existingCartItem != null) {
                    // Modify the existing cart item with updated details
                    existingCartItem.quantity = 2 // Replace with the updated quantity

                    // Update the cart item in the ViewModel
                    cartViewModel.updateCartItemQuantity(existingCartItem, 2)
                } else {
                    // Handle the case where the item with the specified product ID is not found
                    Toast.makeText(requireContext(), "Item not found in the cart", Toast.LENGTH_SHORT).show()
                }
            }
        }


        // Example: Trigger delete by product ID when a button is clicked
        binding.deleteButton.setOnClickListener {
            val productIdToDelete = "your_product_id" // Replace with the actual product ID
            cartViewModel.deleteByProductId(productIdToDelete)
        }

        // Example: Trigger delete all when a button is clicked
        binding.deleteAllButton.setOnClickListener {
            cartViewModel.deleteAll()
        }

        val sampleCartItem = CartItem(
            productName = "Sample Product",
            price = 9.99,
            quantity = 1,
            productId = "sample_product_id"
        )

        onQuantityChanged(cartItem = sampleCartItem, newQuantity = 2)
        onRemoveItemClicked(cartItem = sampleCartItem)

        // Other UI setup or event handling as needed
    }

    private fun showUpdateDialog(cartItem: CartItem, newQuantity: Int) {
        val dialog = CartUpdateDialog(requireContext()) { quantity ->
            // Handle quantity update
            cartItem.quantity = quantity
            cartViewModel.updateCartItemQuantity(cartItem, newQuantity)
        }
        dialog.show()
    }

    private fun updateTotalPrice(cartItems: List<CartItem>) {
        // Calculate and display the total price based on cart items
        val totalPrice = cartItems.sumByDouble { it.price * it.quantity }
        binding.tvTotalPrice.text = "Total Price: $$totalPrice"
    }

    private fun onQuantityChanged(cartItem: CartItem, newQuantity: Int) {
        // Your implementation for quantity change
        cartViewModel.updateCartItemQuantity(cartItem, newQuantity)

        // Optionally, you can update the UI to reflect the change in the cart
        updateTotalPrice(cartViewModel.allItems.value ?: emptyList())
    }


    private fun onRemoveItemClicked(cartItem: CartItem) {
        // Your implementation for item removal
        cartViewModel.deleteByProductId(cartItem.productId)
    }
}

