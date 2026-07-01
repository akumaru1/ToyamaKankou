package com.example.travelapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.travelapp.Sight
import com.example.travelapp.data.CategoryRepository

/**
 * ViewModel for [com.example.travelapp.DetailFragment].
 *
 * Separates data concerns from the Fragment, keeping the parsed [Sight] list
 * and the selected position alive across configuration changes.  The Fragment
 * simply observes [sight] and re-binds views without re-loading or re-parsing
 * the JSON on every rotation.
 */
class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CategoryRepository(application)

    private val _sights = MutableLiveData<List<Sight>>()

    private val _currentPosition = MutableLiveData<Int>()
    /** The index of the [Sight] currently displayed in the detail view. */
    val currentPosition: LiveData<Int> = _currentPosition

    private val _sight = MutableLiveData<Sight?>()
    /** The [Sight] that should be rendered. Null when not yet loaded. */
    val sight: LiveData<Sight?> = _sight

    /** Tracks the file that is currently backing this ViewModel. */
    private var loadedFileName: String? = null

    /**
     * Load [jsonFileName] (once per ViewModel lifetime) and expose the item at
     * [position].  Safe to call repeatedly: the data-load is skipped when the
     * file is already cached; only the position is updated.
     */
    fun load(jsonFileName: String, position: Int) {
        if (jsonFileName != loadedFileName || _sights.value == null) {
            loadedFileName = jsonFileName
            _sights.value = repository.getSightsByFileName(jsonFileName)
        }
        setPosition(position)
    }

    /**
     * Navigate to a different item inside the already-loaded list.
     * Does nothing if [position] is out of range.
     */
    fun setPosition(position: Int) {
        val list = _sights.value ?: return
        if (position < 0 || position >= list.size) return
        _currentPosition.value = position
        _sight.value = list[position]
    }
}
