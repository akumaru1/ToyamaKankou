package com.example.travelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.travelapp.util.LocalizationManager

/**
 * MannerActivity displays the etiquette/manner categories.
 * Shows 3 types of Japanese etiquette: train, onsen, and food manners.
 */
class MannerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Use language-appropriate layout
        setContentView(getLayoutForLanguage())
        
        setupMannerButtons()
    }
    
    private fun getLayoutForLanguage(): Int {
        return when (LocalizationManager.getCurrentLanguage()) {
            LocalizationManager.LANGUAGE_JAPANESE -> R.layout.activity_manner_jp
            LocalizationManager.LANGUAGE_CHINESE -> R.layout.activity_manner_cn
            else -> R.layout.activity_manner_en
        }
    }
    
    private fun setupMannerButtons() {
        // Train manner button
        findViewById<Button>(R.id.train_btn).setOnClickListener {
            val intent = Intent(this, DenshaActivity::class.java)
            startActivity(intent)
        }
        
        // Onsen manner button  
        findViewById<Button>(R.id.onsen_btn).setOnClickListener {
            val intent = Intent(this, OnsenActivity::class.java)
            startActivity(intent)
        }
        
        // Food/eating manner button
        findViewById<Button>(R.id.eat_btn).setOnClickListener {
            val intent = Intent(this, FoodmanaActivity::class.java)
            startActivity(intent)
        }
    }
}