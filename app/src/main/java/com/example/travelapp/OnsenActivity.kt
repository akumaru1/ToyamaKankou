package com.example.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travelapp.util.LocalizationManager

/**
 * OnsenActivity displays hot spring etiquette information.
 * Shows onsen manners and bathing etiquette guides in multiple languages.
 */
class OnsenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Use language-appropriate layout
        setContentView(getLayoutForLanguage())
    }
    
    private fun getLayoutForLanguage(): Int {
        return when (LocalizationManager.getCurrentLanguage()) {
            LocalizationManager.LANGUAGE_JAPANESE -> R.layout.activity_onsen_jp
            LocalizationManager.LANGUAGE_CHINESE -> R.layout.activity_onsen_cn
            else -> R.layout.activity_onsen_en
        }
    }
}