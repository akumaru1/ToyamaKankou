package com.example.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travelapp.data.CategoryConstants
import com.example.travelapp.data.CategoryRepository
import com.example.travelapp.util.LocalizationManager

/**
 * Unified ListActivity that replaces all category-specific activities 
 * (EventJp/En/Cn, BunkaJp/En/Cn, MannerJp/En/Cn, etc.).
 * Generic list activity that loads data based on category and region parameters.
 */
class ListActivity : AppCompatActivity() {

    private lateinit var categoryRepository: CategoryRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutForActivity())

        categoryRepository = CategoryRepository(this)
        
        val categoryId = intent.getStringExtra("CATEGORY_ID")
        val region = intent.getStringExtra("REGION")
        
        loadListFragment(categoryId, region)
    }
    
    private fun getLayoutForActivity(): Int {
        // Use appropriate layout based on category or default
        return R.layout.activity_event_jp // This layout should work for all list activities
    }
    
    private fun loadListFragment(categoryId: String?, region: String?) {
        val jsonFileName = if (categoryId != null) {
            if (region != null) {
                // Build filename from category and region
                categoryRepository.run {
                    when (categoryId) {
                        CategoryConstants.CATEGORY_ATTRACTIONS -> {
                            when (region) {
                                CategoryConstants.REGION_HIMI -> "kankou1${LocalizationManager.getCurrentLanguageSuffix()}.json"
                                CategoryConstants.REGION_TOYAMA -> "kankou2${LocalizationManager.getCurrentLanguageSuffix()}.json"
                                CategoryConstants.REGION_KUROBE -> "kankou3${LocalizationManager.getCurrentLanguageSuffix()}.json"
                                else -> "kankou1${LocalizationManager.getCurrentLanguageSuffix()}.json"
                            }
                        }
                        CategoryConstants.CATEGORY_FOOD -> {
                            when (region) {
                                CategoryConstants.REGION_HIMI -> "gurume1${LocalizationManager.getCurrentLanguageSuffix()}.json"
                                CategoryConstants.REGION_TOYAMA -> "gurume2${LocalizationManager.getCurrentLanguageSuffix()}.json"
                                CategoryConstants.REGION_KUROBE -> "gurume3${LocalizationManager.getCurrentLanguageSuffix()}.json"
                                else -> "gurume1${LocalizationManager.getCurrentLanguageSuffix()}.json"
                            }
                        }
                        else -> getJsonFileNameForCategory(categoryId)
                    }
                }
            } else {
                // Direct category without region
                getJsonFileNameForCategory(categoryId)
            }
        } else {
            // Fallback - use passed JSON_FILE_NAME or default
            intent.getStringExtra("JSON_FILE_NAME") ?: "event${LocalizationManager.getCurrentLanguageSuffix()}.json"
        }

        val tag = "ListFragment"
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            fragment = ListFragment.newInstance(jsonFileName)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.content, fragment, tag)
            }.commit()
        }
    }
    
    private fun getJsonFileNameForCategory(categoryId: String): String {
        val suffix = LocalizationManager.getCurrentLanguageSuffix()
        return when (categoryId) {
            CategoryConstants.CATEGORY_EVENTS -> "event$suffix.json"
            CategoryConstants.CATEGORY_CULTURE -> "bunka$suffix.json"
            CategoryConstants.CATEGORY_MANNER -> "manner$suffix.json"
            CategoryConstants.CATEGORY_ONSEN -> "onsen$suffix.json"
            CategoryConstants.CATEGORY_TRANSPORTATION -> "densha$suffix.json"
            else -> "event$suffix.json"
        }
    }
}