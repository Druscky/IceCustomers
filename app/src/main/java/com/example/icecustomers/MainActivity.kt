package com.example.icecustomers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.icecustomers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.itNew -> {
                    findNavController(R.id.fragmentContainerView)
                        .navigate(R.id.newContact)
                    true
                }
                R.id.itDelete -> {
                    findNavController(R.id.fragmentContainerView)
                        .navigate(R.id.deleteContact)
                    true
                }
                R.id.itUpdate -> {
                    findNavController(R.id.fragmentContainerView)
                        .navigate(R.id.updateContact)
                    true
                }
                R.id.itList -> {
                    findNavController(R.id.fragmentContainerView)
                        .navigate(R.id.listContact)
                    true
                } else -> false
            }
        }
    }
}