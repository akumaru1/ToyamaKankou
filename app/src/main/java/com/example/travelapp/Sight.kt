package com.example.travelapp

import android.content.res.Resources
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader

@Serializable
class Sight(
    val name: String,
    val imageName: String,
    val description: String,
    val kind: String
)

fun getSights(resources: Resources, jsonFileName: String): List<Sight> {
    val assetManager = resources.assets
    val inputStream = assetManager.open(jsonFileName)
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
    val str: String = bufferedReader.readText()

    return try {
        Json.decodeFromString<List<Sight>>(str)
    } catch (e: Exception) {
        // Handle the exception (e.g., log or return an empty list)
        emptyList()
    }
}

