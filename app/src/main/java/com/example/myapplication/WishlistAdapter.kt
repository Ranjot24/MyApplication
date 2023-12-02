package com.example.myapplication

// WishlistAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(private val onItemClicked: (WishlistItem) -> Unit) : ListAdapter<WishlistItem, WishlistAdapter.WishlistViewHolder>(WishlistItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.wishlist_item_layout, parent, false)
        return WishlistViewHolder(view)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class WishlistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productNameTextView: TextView = itemView.findViewById(R.id.productNameTextView) // Replace with actual IDs

        fun bind(wishlistItem: WishlistItem) {
            productNameTextView.text = wishlistItem.productName

            itemView.setOnClickListener {
                onItemClicked(wishlistItem)
            }
            // Add other bindings or click listeners as needed
        }
    }
}

class WishlistItemDiffCallback : DiffUtil.ItemCallback<WishlistItem>() {
    override fun areItemsTheSame(oldItem: WishlistItem, newItem: WishlistItem): Boolean {
        return oldItem.productId == newItem.productId
    }

    override fun areContentsTheSame(oldItem: WishlistItem, newItem: WishlistItem): Boolean {
        return oldItem == newItem
    }
}


