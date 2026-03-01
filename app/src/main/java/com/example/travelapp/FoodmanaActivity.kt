package com.example.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travelapp.util.LocalizationManager

/**
 * FoodmanaActivity displays food etiquette information.
 * Shows dining manners and table etiquette guides in multiple languages.
 */
class FoodmanaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Use language-appropriate layout
        setContentView(getLayoutForLanguage())
    }
    
    private fun getLayoutForLanguage(): Int {
        return when (LocalizationManager.getCurrentLanguage()) {
            LocalizationManager.LANGUAGE_JAPANESE -> R.layout.activity_foodmana_jp
            LocalizationManager.LANGUAGE_CHINESE -> R.layout.activity_foodmana_cn
            else -> R.layout.activity_foodmana_en
        }
    }
}