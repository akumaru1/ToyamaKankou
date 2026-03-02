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
 * All region selections route through [ListActivity] — no more category-specific list activities.
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

        btnHimi.setOnClickListener { startListActivity(CategoryConstants.REGION_HIMI) }
        btnToyama.setOnClickListener { startListActivity(CategoryConstants.REGION_TOYAMA) }
        btnKurobe.setOnClickListener { startListActivity(CategoryConstants.REGION_KUROBE) }
    }

    /**
     * All categories (attractions, food, etc.) now go through [ListActivity],
     * which delegates filename resolution to [com.example.travelapp.data.CategoryRepository].
     */
    private fun startListActivity(region: String) {
        val intent = Intent(this, ListActivity::class.java).apply {
            putExtra("CATEGORY_ID", categoryId)
            putExtra("REGION", region)
        }
        startActivity(intent)
    }
}