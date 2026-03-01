package com.example.travelapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.travelapp.util.LocalizationManager

class Language : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize LocalizationManager
        LocalizationManager.initialize(this)
        
        setContentView(R.layout.activity_language)

        val japaneseButton = findViewById<View>(R.id.button)
        val englishButton = findViewById<View>(R.id.button2)
        val chineseButton = findViewById<View>(R.id.button3)

        japaneseButton.setOnClickListener {
            LocalizationManager.setLanguage(this, LocalizationManager.LANGUAGE_JAPANESE)
            startActivity(Intent(this@Language, MainActivity::class.java))
        }

        englishButton.setOnClickListener {
            LocalizationManager.setLanguage(this, LocalizationManager.LANGUAGE_ENGLISH)
            startActivity(Intent(this@Language, MainActivity::class.java))
        }

        chineseButton.setOnClickListener {
            LocalizationManager.setLanguage(this, LocalizationManager.LANGUAGE_CHINESE)
            startActivity(Intent(this@Language, MainActivity::class.java))
        }
    }
}
