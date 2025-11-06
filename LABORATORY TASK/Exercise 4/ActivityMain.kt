package com.example.mainactivity

import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
