package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class ProductListActivity : AppCompatActivity() {

    private val productList = mutableListOf<Product>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        recyclerView = findViewById(R.id.recyclerView) // Replace with your RecyclerView ID
        productAdapter = ProductAdapter(productList) // Create an adapter with the productList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = productAdapter
        // Using Volley for simplicity
        val queue = Volley.newRequestQueue(this)
        val url = "http://your-node-server/products"

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                try {
                    val productsJsonArray = response.getJSONArray("products")
                    for (i in 0 until productsJsonArray.length()) {
                        val productJson = productsJsonArray.getJSONObject(i)
                        val product = Product(
                            productJson.getString("id"),
                            productJson.getString("name"),
                            productJson.getString("description"),
                            productJson.getDouble("price"),
                            productJson.getString("imageURL")
                        )
                        productList.add(product)
                    }
                    // Update UI with productList
                    updateUI(productList)
                } catch (e: JSONException) {
                    Log.e("ProductListActivity", "Error parsing JSON: $e")
                }
            },
            { error ->
                handleError(error)
            })

        queue.add(request)
    }


    private fun updateUI(productList: List<Product>) {
        // Implement the UI update logic here
        // You can use productList to populate a RecyclerView or any other UI component
        productAdapter.updateProducts(productList)
    }

    private fun handleError(error: VolleyError) {
        if (error.networkResponse != null) {
            // Network error, get additional details
            val statusCode = error.networkResponse.statusCode
            val responseBody = String(error.networkResponse.data)

            // Now you can use statusCode and responseBody to handle the error
            // For example, log the details, show an error message to the user, etc.
            Log.e("ProductListActivity", "Status Code: $statusCode, Response: $responseBody")
        } else {
            // Other errors, such as timeout, server unreachable, etc.
            Log.e("ProductListActivity", "Error: ${error.message}")
        }
    }
}
