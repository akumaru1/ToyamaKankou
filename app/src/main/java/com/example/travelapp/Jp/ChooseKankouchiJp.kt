package com.example.travelapp.Jp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.travelapp.Kankouchi
import com.example.travelapp.R

class ChooseKankouchiJp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_jp)

        val btnHimi: Button = findViewById(R.id.himi_btn)
        val btnToyama: Button = findViewById(R.id.toyama_btn)
        val btnKurobe: Button = findViewById(R.id.kurobe_btn)

        btnHimi.setOnClickListener {
            startKankouchiActivity("kankou1jp.json")
        }

        btnToyama.setOnClickListener {
            startKankouchiActivity("kankou2jp.json")
        }

        btnKurobe.setOnClickListener {
            startKankouchiActivity("kankou3jp.json")
        }
    }

    private fun startKankouchiActivity(jsonFileName: String) {
        val intent = Intent(this, Kankouchi::class.java)
        intent.putExtra("JSON_FILE_NAME", jsonFileName)
        startActivity(intent)
    }
}
