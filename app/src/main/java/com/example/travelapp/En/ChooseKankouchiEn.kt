package com.example.travelapp.En

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.travelapp.Kankouchi
import com.example.travelapp.R

class ChooseKankouchiEn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_en)

        val btnHimi: Button = findViewById(R.id.himi_btn)
        val btnToyama: Button = findViewById(R.id.toyama_btn)
        val btnKurobe: Button = findViewById(R.id.kurobe_btn)

        btnHimi.setOnClickListener {
            startKankouchiActivity("kankou1en.json")
        }

        btnToyama.setOnClickListener {
            startKankouchiActivity("kankou2en.json")
        }

        btnKurobe.setOnClickListener {
            startKankouchiActivity("kankou3en.json")
        }
    }

    private fun startKankouchiActivity(jsonFileName: String) {
        val intent = Intent(this, Kankouchi::class.java)
        intent.putExtra("JSON_FILE_NAME", jsonFileName)
        startActivity(intent)
    }
}
