package com.example.myapplication

// CategoryFragment.kt
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryFragment : Fragment() {

    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)

        // Sample list of categories
        val categories = listOf(
            Category(1, "Electronics", "url_to_electronics_image"),
            Category(2, "Clothing", "url_to_clothing_image"),
            // Add more categories as needed
        )

        // Initialize RecyclerView and Adapter
        categoryAdapter = CategoryAdapter { selectedCategory ->
            // Handle category item click
            val action = CategoryFragmentDirections.actionCategoryFragmentToDetailsFragment(categoryId = selectedCategory.id)
            findNavController().navigate(action)
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.categoryRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = categoryAdapter

        // Submit the list to the adapter
        categoryAdapter.submitList(categories)

        return view
    }
}

