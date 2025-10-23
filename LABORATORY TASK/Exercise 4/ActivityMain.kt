package com.example.mainactivity

import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity

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

        val btnStandard: Button = findViewById(R.id.btnStandard)
        val btnImage: ImageButton = findViewById(R.id.btnImage)
        val btnToggle: ToggleButton = findViewById(R.id.btnToggle)

        btnStandard.setOnClickListener {
            Toast.makeText(this, "Standard Button Clicked!", Toast.LENGTH_SHORT).show()
        }

        btnImage.setOnClickListener {
            Toast.makeText(this, "Image Button Clicked!", Toast.LENGTH_SHORT).show()
        }

        btnToggle.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Toggle ON" else "Toggle OFF"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}package com.example.mainactivity

import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity

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

        val btnStandard: Button = findViewById(R.id.btnStandard)
        val btnImage: ImageButton = findViewById(R.id.btnImage)
        val btnToggle: ToggleButton = findViewById(R.id.btnToggle)

        btnStandard.setOnClickListener {
            Toast.makeText(this, "Standard Button Clicked!", Toast.LENGTH_SHORT).show()
        }

        btnImage.setOnClickListener {
            Toast.makeText(this, "Image Button Clicked!", Toast.LENGTH_SHORT).show()
        }

        btnToggle.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Toggle ON" else "Toggle OFF"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}
