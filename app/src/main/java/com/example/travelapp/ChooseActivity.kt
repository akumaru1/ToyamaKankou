package com.example.travelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.travelapp.data.CategoryConstants
import com.example.travelapp.util.LocalizationManager

/**
 * Unified ChooseActivity that replaces ChooseKankouchiJp/En/Cn and ChooseShokujiJp/En/Cn.
 * Shows region selection for categories that have regional data.
 */
class ChooseActivity : AppCompatActivity() {

    private lateinit var categoryId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        categoryId = intent.getStringExtra("CATEGORY_ID") ?: CategoryConstants.CATEGORY_ATTRACTIONS
        
        // Apply localization and set layout
        setContentView(getLayoutForLanguage())
        
        setupRegionButtons()
    }
    
    private fun getLayoutForLanguage(): Int {
        return when (LocalizationManager.getCurrentLanguage()) {
            LocalizationManager.LANGUAGE_JAPANESE -> R.layout.activity_choose_jp
            LocalizationManager.LANGUAGE_CHINESE -> R.layout.activity_choose_cn  
            else -> R.layout.activity_choose_en
        }
    }
    
    private fun setupRegionButtons() {
        val btnHimi: Button = findViewById(R.id.himi_btn)
        val btnToyama: Button = findViewById(R.id.toyama_btn) 
        val btnKurobe: Button = findViewById(R.id.kurobe_btn)

        btnHimi.setOnClickListener {
            startListActivity(CategoryConstants.REGION_HIMI)
        }

        btnToyama.setOnClickListener {
            startListActivity(CategoryConstants.REGION_TOYAMA)
        }

        btnKurobe.setOnClickListener {
            startListActivity(CategoryConstants.REGION_KUROBE)
        }
    }

    private fun startListActivity(region: String) {
        val intent = when (categoryId) {
            CategoryConstants.CATEGORY_ATTRACTIONS -> {
                Intent(this, Kankouchi::class.java).apply {
                    putExtra("JSON_FILE_NAME", getJsonFileNameForRegion(region))
                }
            }
            CategoryConstants.CATEGORY_FOOD -> {
                Intent(this, Shokuji::class.java).apply {
                    putExtra("JSON_FILE_NAME", getJsonFileNameForRegion(region))
                }
            }
            else -> {
                Intent(this, ListActivity::class.java).apply {
                    putExtra("CATEGORY_ID", categoryId)
                    putExtra("REGION", region)
                }
            }
        }
        startActivity(intent)
    }
    
    private fun getJsonFileNameForRegion(region: String): String {
        val languageSuffix = LocalizationManager.getCurrentLanguageSuffix()
        
        return when (categoryId) {
            CategoryConstants.CATEGORY_ATTRACTIONS -> {
                when (region) {
                    CategoryConstants.REGION_HIMI -> "kankou1$languageSuffix.json"
                    CategoryConstants.REGION_TOYAMA -> "kankou2$languageSuffix.json"
                    CategoryConstants.REGION_KUROBE -> "kankou3$languageSuffix.json"
                    else -> "kankou1$languageSuffix.json"
                }
            }
            CategoryConstants.CATEGORY_FOOD -> {
                when (region) {
                    CategoryConstants.REGION_HIMI -> "gurume1$languageSuffix.json"
                    CategoryConstants.REGION_TOYAMA -> "gurume2$languageSuffix.json" 
                    CategoryConstants.REGION_KUROBE -> "gurume3$languageSuffix.json"
                    else -> "gurume1$languageSuffix.json"
                }
            }
            else -> "kankou1$languageSuffix.json"
        }
    }
}