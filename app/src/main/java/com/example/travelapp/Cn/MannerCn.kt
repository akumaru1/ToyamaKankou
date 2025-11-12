package com.example.travelapp.Cn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.travelapp.Jp.DenshaJp
import com.example.travelapp.Jp.FoodmanaJp
import com.example.travelapp.Jp.OnsenJp
import com.example.travelapp.R

class MannerCn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manner_cn)

        val onsen_btn = findViewById<View>(R.id.onsen_btn)
        onsen_btn.setOnClickListener {
            val intent = Intent(this, OnsenCn::class.java)
            startActivity(intent)
        }

        val train_btn = findViewById<View>(R.id.train_btn)
        train_btn.setOnClickListener {
            val intent = Intent(this, DenshaCn::class.java)
            startActivity(intent)
        }

        val eat_btn = findViewById<View>(R.id.eat_btn)
        eat_btn.setOnClickListener {
            val intent = Intent(this, FoodmanaCn::class.java)
            startActivity(intent)
        }
    }
}