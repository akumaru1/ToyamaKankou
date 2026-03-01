package com.example.travelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.travelapp.data.CategoryConstants
import com.example.travelapp.util.LocalizationManager

/**
 * Unified MainActivity that replaces MainActivityJp/En/Cn.
 * Shows category selection UI with proper localization.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Apply localization
        val localizedContext = LocalizationManager.createLocalizedContext(this)
        setContentView(getLayoutForLanguage())

        setupCategoryButtons()
        setupRecommendationButtons() 
    }
    
    private fun getLayoutForLanguage(): Int {
        return when (LocalizationManager.getCurrentLanguage()) {
            LocalizationManager.LANGUAGE_JAPANESE -> R.layout.activity_main_jp
            LocalizationManager.LANGUAGE_CHINESE -> R.layout.activity_main_cn
            else -> R.layout.activity_main_en
        }
    }
    
    private fun setupCategoryButtons() {
        // Attractions (Kankouchi) - with region selection
        findViewById<ImageButton>(R.id.btnKankouchi).setOnClickListener {
            startChooseActivity(CategoryConstants.CATEGORY_ATTRACTIONS)
        }

        // Food (Shokuji) - with region selection  
        findViewById<ImageButton>(R.id.btnFood).setOnClickListener {
            startChooseActivity(CategoryConstants.CATEGORY_FOOD)
        }

        // Manner - redirect to special manner screen
        findViewById<ImageButton>(R.id.btnManner).setOnClickListener {
            val intent = Intent(this, MannerActivity::class.java)
            startActivity(intent)
        }

        // Events - direct to list
        findViewById<ImageButton>(R.id.btnEvent).setOnClickListener {
            startListActivity(CategoryConstants.CATEGORY_EVENTS, null)
        }

        // Culture - direct to list
        findViewById<ImageButton>(R.id.btnBunka).setOnClickListener {
            startListActivity(CategoryConstants.CATEGORY_CULTURE, null)
        }
    }
    
    private fun setupRecommendationButtons() {
        // Recommendation buttons (osusume) - direct to detail view
        findViewById<ImageButton>(R.id.btnkansui)?.setOnClickListener {
            startDetailActivity("kankou2", 0)
        }

        findViewById<ImageButton>(R.id.btnAma)?.setOnClickListener {
            startDetailActivity("kankou1", 0) 
        }

        findViewById<ImageButton>(R.id.btnkurobe)?.setOnClickListener {
            startDetailActivity("kankou3", 4)
        }

        findViewById<ImageButton>(R.id.btnkaze)?.setOnClickListener {
            startDetailActivity("event", 3)
        }

        findViewById<ImageButton>(R.id.btnhotaru)?.setOnClickListener {  
            startDetailActivity("event", 1)
        }

        findViewById<ImageButton>(R.id.btnramen)?.setOnClickListener {
            startDetailActivity("gurume2", 1)
        }

        findViewById<ImageButton>(R.id.btnglass)?.setOnClickListener {
            startDetailActivity("bunka", 3)
        }
    }
    
    private fun startChooseActivity(categoryId: String) {
        val intent = Intent(this, ChooseActivity::class.java).apply {
            putExtra("CATEGORY_ID", categoryId)
        }
        startActivity(intent)
    }
    
    private fun startListActivity(categoryId: String, region: String?) {
        val intent = Intent(this, ListActivity::class.java).apply {
            putExtra("CATEGORY_ID", categoryId)
            region?.let { putExtra("REGION", it) }
        }
        startActivity(intent)
    }
    
    private fun startDetailActivity(baseFileName: String, position: Int) {
        val languageSuffix = LocalizationManager.getCurrentLanguageSuffix()
        val jsonFileName = "${baseFileName}${languageSuffix}.json"
        
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("JSON_FILE_NAME", jsonFileName)
            putExtra(ROW_POSITION, position)
        }
        startActivity(intent)
    }
}