package com.example.travelapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.travelapp.Cn.MainActivityCn
import com.example.travelapp.En.MainActivityEn
import com.example.travelapp.Jp.MainActivityJp

class Language : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        val japaneseButton = findViewById<View>(R.id.button)
        val englishButton = findViewById<View>(R.id.button2)
        val chineseButton = findViewById<View>(R.id.button3)

        japaneseButton.setOnClickListener {
            startActivity(Intent(this@Language, MainActivityJp::class.java))
        }

        englishButton.setOnClickListener {
            startActivity(Intent(this@Language, MainActivityEn::class.java))
        }

        chineseButton.setOnClickListener {
            startActivity(Intent(this@Language, MainActivityCn::class.java))
        }
    }
}
