package com.example.toyamakankou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* 画面移動のため */
        val btnKankouchi : ImageButton = findViewById(R.id.btnKankouchi);
        btnKankouchi.setOnClickListener {
            val intent = Intent(this,Kankouchi::class.java)
            startActivity(intent)
        }
    }
}