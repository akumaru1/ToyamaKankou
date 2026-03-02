package com.example.travelapp

import kotlinx.serialization.Serializable

/**
 * Data model representing a single tourism sight/attraction.
 * Serialized from JSON assets via [com.example.travelapp.data.CategoryRepository].
 */
@Serializable
data class Sight(
    val name: String,
    val imageName: String,
    val description: String,
    val kind: String
)
