package com.example.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travelapp.util.LocalizationManager

/**
 * DenshaActivity displays train etiquette information.
 * Shows train manners and public transportation etiquette guides in multiple languages.
 */
class DenshaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Use language-appropriate layout
        setContentView(getLayoutForLanguage())
    }
    
    private fun getLayoutForLanguage(): Int {
        return when (LocalizationManager.getCurrentLanguage()) {
            LocalizationManager.LANGUAGE_JAPANESE -> R.layout.activity_densha_jp
            LocalizationManager.LANGUAGE_CHINESE -> R.layout.activity_densha_cn
            else -> R.layout.activity_densha_en
        }
    }
}