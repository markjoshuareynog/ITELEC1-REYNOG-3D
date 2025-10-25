package com.example.mainactivity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etTask: EditText
    private lateinit var btnAdd: Button
    private lateinit var tvList: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etTask = findViewById(R.id.etTask)
        btnAdd = findViewById(R.id.btnAdd)
        tvList = findViewById(R.id.tvList)

        btnAdd.setOnClickListener {
            val task = etTask.text.toString().trim()
            if (task.isNotEmpty()) {
                val currentList = tvList.text.toString()
                tvList.text = "$currentList\n• $task"
                etTask.text.clear()
            } else {
                etTask.error = "Please enter a task"
            }
        }
    }
}
