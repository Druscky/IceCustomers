package com.example.icecustomers.Core

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.icecustomers.R

class GetSpinner {
    private var _spinner:Spinner
    private var _selectedOption = arrayOf("")

    constructor(spinner: Spinner, selectedOption:Array<String>, idVals:Int) {
        this._spinner = spinner
        this._selectedOption = selectedOption
        val mAdapter = ArrayAdapter.createFromResource(spinner.context, idVals, R.layout.spinner_items)
        this._spinner.adapter = mAdapter
        setOnSelect()
    }
    constructor(spinner: Spinner, selectedOption:Array<String>, arrVals:Array<String>) {
        this._spinner = spinner
        this._selectedOption = selectedOption
        val mAdapter = ArrayAdapter(spinner.context, R.layout.spinner_items, arrVals)
        this._spinner.adapter = mAdapter
        setOnSelect()
    }

    fun setOnSelect(){
        _spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                _selectedOption[0] = parent?.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                _selectedOption[0] = "Sin selección"
            }
        }
    }
}