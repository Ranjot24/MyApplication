package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class WishlistUpdateDialog(
    context: Context,
    private val onUpdateQuantity: (Int) -> Unit
) : Dialog(context) {

    private lateinit var etNewQuantity: EditText
    private lateinit var btnUpdate: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_wishlist_update, null)
        setContentView(view)

        etNewQuantity = view.findViewById(R.id.etNewQuantity)
        btnUpdate = view.findViewById(R.id.btnUpdate)
        btnCancel = view.findViewById(R.id.btnCancel)

        btnUpdate.setOnClickListener {
            val newQuantity = etNewQuantity.text.toString().toIntOrNull()
            if (newQuantity != null) {
                onUpdateQuantity(newQuantity)
                dismiss()
            } else {
                // Handle invalid input, e.g., show an error message
                etNewQuantity.error = "Invalid quantity"
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
