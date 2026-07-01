package com.example.travelapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel for [com.example.travelapp.MainActivity].
 * Survives configuration changes (rotation) and keeps track of the selected
 * language and category so the UI can be restored without re-querying.
 */
class MainViewModel : ViewModel() {

    private val _selectedLanguage = MutableLiveData<String>()
    /** The currently-selected language code (e.g. "ja", "en", "zh"). */
    val selectedLanguage: LiveData<String> = _selectedLanguage

    private val _selectedCategory = MutableLiveData<String>()
    /** The category ID last chosen by the user. */
    val selectedCategory: LiveData<String> = _selectedCategory

    /** Call when the user switches the app language. */
    fun setLanguage(languageCode: String) {
        _selectedLanguage.value = languageCode
    }

    /** Call when the user selects a tourism category. */
    fun setCategory(categoryId: String) {
        _selectedCategory.value = categoryId
    }
}
