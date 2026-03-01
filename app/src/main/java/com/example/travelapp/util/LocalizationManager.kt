package com.example.travelapp.util

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import java.util.*

/**
 * Manages localization and language switching throughout the app.
 * Singleton pattern to ensure consistent language state across activities.
 */
object LocalizationManager {
    
    private const val PREFS_NAME = "language_prefs"
    private const val KEY_LANGUAGE = "selected_language"
    
    // Language codes
    const val LANGUAGE_JAPANESE = "ja"
    const val LANGUAGE_ENGLISH = "en" 
    const val LANGUAGE_CHINESE = "zh"
    
    private var currentLanguage: String? = null
    private lateinit var sharedPrefs: SharedPreferences
    
    /**
     * Initialize the localization manager with app context
     */
    fun initialize(context: Context) {
        sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        currentLanguage = getSavedLanguage() ?: getSystemLanguage()
    }
    
    /**
     * Get the current language code
     */
    fun getCurrentLanguage(): String {
        return currentLanguage ?: LANGUAGE_ENGLISH
    }
    
    /**
     * Set the current language and save preference
     */
    fun setLanguage(context: Context, languageCode: String) {
        currentLanguage = languageCode
        saveLanguage(languageCode)
        updateConfiguration(context, languageCode)
    }
    
    /**
     * Get the JSON file suffix for the current language
     */
    fun getCurrentLanguageSuffix(): String {
        return when (getCurrentLanguage()) {
            LANGUAGE_JAPANESE -> "jp"
            LANGUAGE_CHINESE -> "cn" 
            else -> "en"
        }
    }
    
    /**
     * Build JSON file name with current language suffix
     */
    fun getLocalizedJsonFileName(baseName: String): String {
        val suffix = getCurrentLanguageSuffix()
        return "${baseName}${suffix}.json"
    }
    
    /**
     * Create a context with the specified locale
     */
    fun createLocalizedContext(context: Context, languageCode: String = getCurrentLanguage()): Context {
        val locale = Locale(languageCode)
        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)
        return context.createConfigurationContext(configuration)
    }
    
    private fun getSavedLanguage(): String? {
        return if (::sharedPrefs.isInitialized) {
            sharedPrefs.getString(KEY_LANGUAGE, null)
        } else null
    }
    
    private fun saveLanguage(languageCode: String) {
        if (::sharedPrefs.isInitialized) {
            sharedPrefs.edit()
                .putString(KEY_LANGUAGE, languageCode)
                .apply()
        }
    }
    
    private fun getSystemLanguage(): String {
        val systemLanguage = Locale.getDefault().language
        return when (systemLanguage) {
            "ja" -> LANGUAGE_JAPANESE
            "zh" -> LANGUAGE_CHINESE
            else -> LANGUAGE_ENGLISH
        }
    }
    
    private fun updateConfiguration(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)
        context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
    }
}