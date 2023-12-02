package com.example.myapplication

// CartUpdateDialog.kt
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class CartUpdateDialog(
    context: Context,
    private val onUpdateClickListener: (quantity: Int) -> Unit
) : Dialog(context) {

    private lateinit var etQuantity: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_cart_update, null)
        etQuantity = view.findViewById(R.id.etQuantity)

        val btnUpdate = view.findViewById<Button>(R.id.btnUpdate)
        val btnCancel = view.findViewById<Button>(R.id.btnCancel)

        btnUpdate.setOnClickListener {
            val quantity = etQuantity.text.toString().toIntOrNull()
            if (quantity != null && quantity > 0) {
                onUpdateClickListener(quantity)
                dismiss()
            } else {
                // Handle invalid input (non-numeric or negative quantity)
                etQuantity.error = "Invalid quantity"
            }
        }

        btnCancel.setOnClickListener {
            dismiss()
        }

        // Set up the dialog
        setCancelable(true)
        setCanceledOnTouchOutside(true)
        setContentView(view)
    }
}
