package com.example.toyamakankou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* 画面移動のため */
        val btnKankouchi : ImageButton = findViewById(R.id.btnKankouchi)
        btnKankouchi.setOnClickListener {
            val intent = Intent(this, Kankouchi::class.java)
            startActivity(intent)
        }

        bottomNavigationView = findViewById(R.id.BottomNavigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.settings -> {
                    startActivity(Intent(this@MainActivity, Settings::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
