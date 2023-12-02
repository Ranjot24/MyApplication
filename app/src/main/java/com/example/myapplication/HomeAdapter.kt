package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.YourItemType

class HomeAdapter : ListAdapter<YourItemType, HomeAdapter.ViewHolder>(YourItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.textViewItemTitle)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.textViewItemDescription)

        fun bind(item: YourItemType) {
            titleTextView.text = item.title
            descriptionTextView.text = item.description
            val imageUrl = item.imageUrl
            val imageUrlViewItem: TextView = itemView.findViewById(R.id.imageUrlViewItem)
            imageUrlViewItem.text = imageUrl
        }
    }

    class YourItemCallback : DiffUtil.ItemCallback<YourItemType>() {
        override fun areItemsTheSame(oldItem: YourItemType, newItem: YourItemType): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: YourItemType, newItem: YourItemType): Boolean {
            return oldItem == newItem
        }
    }
}
