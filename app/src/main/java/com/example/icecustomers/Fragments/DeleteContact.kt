package com.example.icecustomers.Fragments

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.icecustomers.Core.GetSpinner
import com.example.icecustomers.R
import com.example.icecustomers.SQLiteHelper
import com.example.icecustomers.databinding.FragmentDeleteContactBinding


class DeleteContact : Fragment() {

    private var _b : FragmentDeleteContactBinding? = null
    private val b get() = _b!!
    private lateinit var contactsDBHelper : SQLiteHelper
    private val spnOpt = arrayOf("")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _b = FragmentDeleteContactBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactsDBHelper = SQLiteHelper(this.requireContext())
        GetSpinner(b.spinnerDelete, spnOpt, sqliteToArray())

        b.btnDelete.setOnClickListener {
            val endIndex = spnOpt[0].toString().indexOf(':')
            contactsDBHelper.deleteData(spnOpt[0].toString().substring(0, endIndex))
            GetSpinner(b.spinnerDelete, spnOpt, sqliteToArray())
//            hideKeyboard()
        }
    }

    fun sqliteToArray():Array<String> {
        val sqliteData = arrayListOf<String>()
        // Abro la base de datos en modo LECTURA
        val db : SQLiteDatabase = contactsDBHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${SQLiteHelper.TABLE_NAME}", null)

        // Compruebo si hay algún registro
        if (cursor.moveToFirst()) {
            do {
                sqliteData.add(cursor.getInt(0).toString() + ": " +
                        cursor.getString(1) + " " + cursor.getString(2))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return sqliteData.toTypedArray()
    }
}