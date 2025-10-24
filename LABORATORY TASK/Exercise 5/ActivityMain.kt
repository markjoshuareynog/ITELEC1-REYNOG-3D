package com.example.mainactivity

import android.os.Bundle
import android.widget.Button
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.ViewSwitcher
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var currentImage = 0
    private val images = intArrayOf(
        R.drawable.img1,
        R.drawable.img2
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val staticImage = findViewById<ImageView>(R.id.staticImageView)
        staticImage.setImageResource(R.drawable.img1)

        val imageSwitcher = findViewById<ImageSwitcher>(R.id.imageSwitcher)
        imageSwitcher.setFactory(ViewSwitcher.ViewFactory {
            val imageView = ImageView(this)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView
        })

        imageSwitcher.setImageResource(images[currentImage])

        val btnSwitch = findViewById<Button>(R.id.btnSwitch)
        btnSwitch.setOnClickListener {
            currentImage = (currentImage + 1) % images.size
            imageSwitcher.setImageResource(images[currentImage])
        }
    }
}
