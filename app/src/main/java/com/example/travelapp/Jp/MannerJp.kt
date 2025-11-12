package com.example.travelapp.Jp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.example.travelapp.R

class MannerJp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manner_jp)

        val onsen_btn = findViewById<View>(R.id.onsen_btn)
        onsen_btn.setOnClickListener {
            val intent = Intent(this, OnsenJp::class.java)
            startActivity(intent)
        }

        val train_btn = findViewById<View>(R.id.train_btn)
        train_btn.setOnClickListener {
            val intent = Intent(this, DenshaJp::class.java)
            startActivity(intent)
        }

        val eat_btn = findViewById<View>(R.id.eat_btn)
        eat_btn.setOnClickListener {
            val intent = Intent(this, FoodmanaJp::class.java)
            startActivity(intent)
        }
    }
}