package com.example.travelapp.En

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.travelapp.Jp.DenshaJp
import com.example.travelapp.Jp.FoodmanaJp
import com.example.travelapp.Jp.OnsenJp
import com.example.travelapp.R

class MannerEn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manner_en)

        val onsen_btn = findViewById<View>(R.id.onsen_btn)
        onsen_btn.setOnClickListener {
            val intent = Intent(this, OnsenEn::class.java)
            startActivity(intent)
        }

        val train_btn = findViewById<View>(R.id.train_btn)
        train_btn.setOnClickListener {
            val intent = Intent(this, DenshaEn::class.java)
            startActivity(intent)
        }

        val eat_btn = findViewById<View>(R.id.eat_btn)
        eat_btn.setOnClickListener {
            val intent = Intent(this, FoodmanaEn::class.java)
            startActivity(intent)
        }

    }
}