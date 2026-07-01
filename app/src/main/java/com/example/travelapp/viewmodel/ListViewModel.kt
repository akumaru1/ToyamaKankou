package com.example.travelapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.travelapp.Sight
import com.example.travelapp.data.CategoryRepository

/**
 * ViewModel for [com.example.travelapp.ListFragment].
 *
 * Owns the data lifecycle for a tourism-category list: loads sights once from
 * [CategoryRepository] and caches them across configuration changes so the
 * RecyclerView is never re-populated unnecessarily after a device rotation.
 *
 * Uses [AndroidViewModel] so the repository can be seeded with an
 * application-scoped [Context] that does not leak the UI host.
 */
class ListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CategoryRepository(application)

    private val _sights = MutableLiveData<List<Sight>>()
    /** The currently loaded list of [Sight] items. Observed by [com.example.travelapp.ListFragment]. */
    val sights: LiveData<List<Sight>> = _sights

    /** Tracks which JSON file is currently loaded so we avoid redundant loads. */
    private var loadedFileName: String? = null

    /**
     * Load sights from [jsonFileName].  Subsequent calls with the same filename
     * are no-ops: the already-live data is reused (survives rotation).
     */
    fun loadSights(jsonFileName: String) {
        if (jsonFileName == loadedFileName && _sights.value != null) return
        loadedFileName = jsonFileName
        _sights.value = repository.getSightsByFileName(jsonFileName)
    }
}
