package com.example.myapplication

// AddressEntryActivity.kt
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Address

class AddressEntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_entry)

        val etStreet = findViewById<EditText>(R.id.etStreet)
        val etCity = findViewById<EditText>(R.id.etCity)
        val etState = findViewById<EditText>(R.id.etState)
        val etZipCode = findViewById<EditText>(R.id.etZipcode)
        val btnPlaceOrder = findViewById<Button>(R.id.btnPlaceOrder)

        btnPlaceOrder.setOnClickListener {
                val address = Address(
                    etStreet.text.toString(),
                    etCity.text.toString(),
                    etState.text.toString(),
                    etZipCode.text.toString()
                )

            if (address.isValid()) {
                startOrderSummaryActivity(address)
            } else {
                // Handle the case when the address is not valid
                // You might show an error message or take appropriate action
                // For now, you can log a message or show a Toast
                Toast.makeText(this, "Invalid address. Please check your input.", Toast.LENGTH_SHORT).show()
                etStreet.text.clear()
                etCity.text.clear()
                etState.text.clear()
                etZipCode.text.clear()
            }
        }
    }

    private fun startOrderSummaryActivity(address: Address) {
        val intent = Intent(this, OrderSummaryActivity::class.java)
        intent.putExtra("ADDRESS", address)
        startActivity(intent)
    }
}
