package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.HomeViewModel
import com.example.myapplication.YourItemType
import com.example.myapplication.adapters.HomeAdapter
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel and RecyclerView Adapter
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeAdapter = HomeAdapter()

        // Set up RecyclerView
        binding.recyclerViewHome.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter
        }
        val itemsList: List<YourItemType> = listOf(
            YourItemType(1L, "Item 1", "", ""),
            YourItemType(2L, "Item 2", "", ""))
            // Call the updateHomeItems function
            homeViewModel.updateHomeItems(itemsList)
        // Observe data from the ViewModel
            homeViewModel.homeItems.observe(viewLifecycleOwner, Observer { items ->
            homeAdapter.submitList(items)
        })
    }
}
