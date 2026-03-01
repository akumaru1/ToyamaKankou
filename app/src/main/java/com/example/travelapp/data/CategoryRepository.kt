package com.example.travelapp.data

import android.content.Context
import com.example.travelapp.Sight
import com.example.travelapp.util.LocalizationManager
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

/**
 * Repository for loading tourism data from JSON assets.
 * Centralizes data loading and implements caching to avoid repeated JSON parsing.
 */
class CategoryRepository(private val context: Context) {
    
    // Cache parsed JSON data to avoid re-parsing
    private val sightCache = mutableMapOf<String, List<Sight>>()
    
    /**
     * Get sights for a specific category and region in the current language
     */
    fun getSights(categoryId: String, region: String): List<Sight> {
        val jsonFileName = buildJsonFileName(categoryId, region)
        return getCachedSights(jsonFileName) ?: loadSightsFromJson(jsonFileName).also { sights ->
            sightCache[jsonFileName] = sights
        }
    }
    
    /**
     * Get sights by direct JSON filename (for backwards compatibility)
     */
    fun getSightsByFileName(jsonFileName: String): List<Sight> {
        return getCachedSights(jsonFileName) ?: loadSightsFromJson(jsonFileName).also { sights ->
            sightCache[jsonFileName] = sights
        }
    }
    
    /**
     * Clear the cache (useful for language changes)
     */
    fun clearCache() {
        sightCache.clear()
    }
    
    private fun getCachedSights(jsonFileName: String): List<Sight>? {
        return sightCache[jsonFileName]
    }
    
    private fun loadSightsFromJson(jsonFileName: String): List<Sight> {
        return try {
            val jsonString = context.assets.open("json/$jsonFileName").bufferedReader().use { it.readText() }
            Json.decodeFromString<List<Sight>>(jsonString)
        } catch (e: Exception) {
            // Log error but return empty list to prevent crashes
            e.printStackTrace()
            emptyList()
        }
    }
    
    private fun buildJsonFileName(categoryId: String, region: String): String {
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
            CategoryConstants.CATEGORY_EVENTS -> "event$languageSuffix.json"
            CategoryConstants.CATEGORY_CULTURE -> "bunka$languageSuffix.json"
            CategoryConstants.CATEGORY_MANNER -> "manner$languageSuffix.json"
            CategoryConstants.CATEGORY_ONSEN -> "onsen$languageSuffix.json"
            CategoryConstants.CATEGORY_TRANSPORTATION -> "densha$languageSuffix.json"
            else -> "kankou1$languageSuffix.json"
        }
    }
}