package com.example.icecustomers.Core

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Activity.toast(text:String, length:Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, length).show()
}

fun Fragment.toast(text:String, length:Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.context, text, length).show()
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

//Fragment
//fun hideKeyBoard() {
//    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//    imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
//}

//Activity
//fun hideKeyBoard() {
//    val imm = getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
//    imm.hideSoftInputFromWindow(b.constraintLayoutMain.windowToken, 0)
//}
