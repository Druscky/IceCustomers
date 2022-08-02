package com.example.icecustomers.Fragments

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.icecustomers.Adapter.ContactAdapter
import com.example.icecustomers.Contact
import com.example.icecustomers.R
import com.example.icecustomers.SQLiteHelper
import com.example.icecustomers.databinding.FragmentListContactBinding

class ListContact : Fragment() {

    private var _b : FragmentListContactBinding? = null
    private val b get() = _b!!
    private lateinit var contactsDBHelper : SQLiteHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _b = FragmentListContactBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactsDBHelper = SQLiteHelper(this.requireContext())
        initRecyclerView()
    }

    fun initRecyclerView(){
        b.recyclerContacts.layoutManager = LinearLayoutManager(this.context)
        b.recyclerContacts.adapter = ContactAdapter(sqliteToList())
    }

    fun sqliteToList():MutableList<Contact> {
        // Abro la base de datos en modo LECTURA
        val db : SQLiteDatabase = contactsDBHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${SQLiteHelper.TABLE_NAME}", null)
        val contactList = mutableListOf<Contact>()
        // Compruebo si hay algún registro
        if (cursor.moveToFirst()) {
            do {
                contactList.add(Contact(
                    cursor.getInt(0).toString(),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return contactList
    }

}