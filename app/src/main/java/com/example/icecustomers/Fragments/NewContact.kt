package com.example.icecustomers.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.icecustomers.R
import com.example.icecustomers.SQLiteHelper
import com.example.icecustomers.databinding.FragmentNewContactBinding


class NewContact : Fragment() {


    private var _b : FragmentNewContactBinding? = null
    private val b get() = _b!!
    private lateinit var contactsDBHelper : SQLiteHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _b = FragmentNewContactBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactsDBHelper = SQLiteHelper(this.requireContext())
        b.btnNew.setOnClickListener {
            if (b.etNameNew.text.isNotBlank() &&
                b.etOpeningNew.text.isNotBlank()){
                contactsDBHelper.addData(
                    b.etNameNew.text.toString().uppercase(),
                    b.etAddressNew.text.toString(),
                    b.etOpeningNew.text.toString(),
                    b.etPhoneNew.text.toString())
                b.etNameNew.text.clear()
                b.etAddressNew.text.clear()
                b.etOpeningNew.text.clear()
                b.etPhoneNew.text.clear()
                toast("¡Guardado!")

            } else {
                toast("No se ha podido guardar", Toast.LENGTH_LONG)
            }
//            hideKeyboard()
        }
    }

    fun toast(text:String, length:Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this.context, text, length).show()
    }
}