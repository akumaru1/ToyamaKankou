package com.example.toyamakankou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class Kankouchi : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kankouchi)

        bottomNavigationView = findViewById(R.id.BottomNavigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    startActivity(Intent(this@Kankouchi, MainActivity::class.java))
                    true
                }
                R.id.settings -> {
                    startActivity(Intent(this@Kankouchi, Settings::class.java))
                    true
                }
                else -> false
            }
        }
    }
}