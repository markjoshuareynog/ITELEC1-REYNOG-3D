package com.example.mainactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countries = arrayOf("Philippines", "Japan", "Korea", "USA", "Canada")
        val autoComplete: AutoCompleteTextView = findViewById(R.id.autoCompleteCountry)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, countries)
        autoComplete.setAdapter(adapter)

        val spinner: Spinner = findViewById(R.id.spinnerOptions)
        val spinnerItems = arrayOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter
    }
}
