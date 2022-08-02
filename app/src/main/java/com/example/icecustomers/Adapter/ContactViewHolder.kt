package com.example.icecustomers.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.icecustomers.Contact
import com.example.icecustomers.databinding.ItemContactsBinding

class ContactViewHolder (view: View): RecyclerView.ViewHolder(view){
    val b = ItemContactsBinding.bind(view) // layout_item.xml

    fun render(contactModel: Contact){
        b.cvID.text = contactModel.id
        b.cvName.text = contactModel.name
        b.cvAddress.text = contactModel.address
        b.cvOpening.text = contactModel.opening
        b.cvPhone.text = contactModel.phone
    }
}