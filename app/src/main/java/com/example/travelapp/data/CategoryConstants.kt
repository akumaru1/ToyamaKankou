package com.example.travelapp.data

import com.example.travelapp.R

/**
 * Constants for tourism categories and regions.
 * Centralizes all category/region identifiers and their mappings.
 */
object CategoryConstants {
    
    // Category IDs
    const val CATEGORY_ATTRACTIONS = "kankou"
    const val CATEGORY_FOOD = "shokuji" 
    const val CATEGORY_EVENTS = "event"
    const val CATEGORY_CULTURE = "bunka"
    const val CATEGORY_MANNER = "manner"
    const val CATEGORY_ONSEN = "onsen"
    const val CATEGORY_TRANSPORTATION = "densha"
    
    // Region IDs 
    const val REGION_HIMI = "himi"
    const val REGION_TOYAMA = "toyama"
    const val REGION_KUROBE = "kurobe"
    
    /**
     * Data class representing a tourism category
     */
    data class TourismCategory(
        val id: String,
        val nameResId: Int,
        val hasRegions: Boolean = true
    )
    
    /**
     * All available categories with their display names
     */
    val ALL_CATEGORIES = listOf(
        TourismCategory(CATEGORY_ATTRACTIONS, R.string.category_attractions, hasRegions = true),
        TourismCategory(CATEGORY_FOOD, R.string.category_food, hasRegions = true),  
        TourismCategory(CATEGORY_EVENTS, R.string.category_events, hasRegions = false),
        TourismCategory(CATEGORY_CULTURE, R.string.category_culture, hasRegions = false),
        TourismCategory(CATEGORY_MANNER, R.string.category_manner, hasRegions = false),
        TourismCategory(CATEGORY_ONSEN, R.string.category_onsen, hasRegions = false),
        TourismCategory(CATEGORY_TRANSPORTATION, R.string.category_transportation, hasRegions = false)
    )
    
    /**
     * All available regions with their display names
     */
    data class TourismRegion(
        val id: String,
        val nameResId: Int
    )
    
    val ALL_REGIONS = listOf(
        TourismRegion(REGION_HIMI, R.string.region_himi),
        TourismRegion(REGION_TOYAMA, R.string.region_toyama),
        TourismRegion(REGION_KUROBE, R.string.region_kurobe)
    )
}