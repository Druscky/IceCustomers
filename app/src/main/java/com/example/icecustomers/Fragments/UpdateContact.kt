package com.example.icecustomers.Fragments

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.icecustomers.Core.GetSpinner
import com.example.icecustomers.Core.toast
import com.example.icecustomers.SQLiteHelper
import com.example.icecustomers.databinding.FragmentUpdateContactBinding


class UpdateContact : Fragment() {

    private var _b : FragmentUpdateContactBinding? = null
    private val b get() = _b!!
    private lateinit var contactsDBHelper : SQLiteHelper
    private val spnOpt = arrayOf("")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _b = FragmentUpdateContactBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactsDBHelper = SQLiteHelper(this.requireContext())
        GetSpinner(b.spinnerUpdate, spnOpt, sqliteToArray())

        b.btnUpdate.setOnClickListener {
            val endIndex = spnOpt[0].indexOf(':')
            val id = spnOpt[0].substring(0, endIndex)
            val affectedRows = contactsDBHelper.updateData(id,
                b.etNameUpdate.text.toString().uppercase(),
                b.etAddressUpdate.text.toString(),
                b.etOpeningUpdate.text.toString(),
                b.etPhoneUpdate.text.toString())
//            hideKeyboard()
            b.etNameUpdate.text.clear()
            b.etAddressUpdate.text.clear()
            b.etOpeningUpdate.text.clear()
            b.etPhoneUpdate.text.clear()
            GetSpinner(b.spinnerUpdate, spnOpt, sqliteToArray())
            if (affectedRows > 0) {
                toast("Has modificado $affectedRows registros")
            } else {
                toast("No se han modificado registros")
            }
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
                sqliteData.add(cursor.getInt(0).toString() + ": " + "" + "" +
                        cursor.getString(1))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return sqliteData.toTypedArray()
    }

}