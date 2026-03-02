package com.example.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travelapp.data.CategoryConstants
import com.example.travelapp.data.CategoryRepository
import com.example.travelapp.util.LocalizationManager

/**
 * Unified ListActivity that replaces all category-specific activities
 * (EventJp/En/Cn, BunkaJp/En/Cn, MannerJp/En/Cn, etc.).
 * Data loading is fully delegated to [CategoryRepository]; this activity only
 * handles navigation intent extras and fragment transactions.
 */
class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_jp)

        val categoryId = intent.getStringExtra("CATEGORY_ID")
        val region = intent.getStringExtra("REGION")

        loadListFragment(categoryId, region)
    }

    private fun loadListFragment(categoryId: String?, region: String?) {
        // Resolve the JSON filename through CategoryRepository so the mapping
        // logic lives in exactly one place.
        val repository = CategoryRepository(this)
        val jsonFileName: String = when {
            categoryId != null && region != null ->
                repository.getJsonFileName(categoryId, region)
            categoryId != null ->
                repository.getJsonFileName(categoryId, CategoryConstants.REGION_TOYAMA)
            else ->
                intent.getStringExtra("JSON_FILE_NAME")
                    ?: LocalizationManager.getLocalizedJsonFileName("event")
        }

        val tag = "ListFragment"
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content, ListFragment.newInstance(jsonFileName), tag)
                .commit()
        }
    }
}