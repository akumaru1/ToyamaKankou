package com.example.travelapp.Cn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.travelapp.Kankouchi
import com.example.travelapp.R

class ChooseKankouchiCn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_cn)

        val btnHimi: Button = findViewById(R.id.himi_btn)
        val btnToyama: Button = findViewById(R.id.toyama_btn)
        val btnKurobe: Button = findViewById(R.id.kurobe_btn)

        btnHimi.setOnClickListener {
            startKankouchiActivity("kankou1cn.json")
        }

        btnToyama.setOnClickListener {
            startKankouchiActivity("kankou2cn.json")
        }

        btnKurobe.setOnClickListener {
            startKankouchiActivity("kankou3cn.json")
        }
    }

    private fun startKankouchiActivity(jsonFileName: String) {
        val intent = Intent(this, Kankouchi::class.java)
        intent.putExtra("JSON_FILE_NAME", jsonFileName)
        startActivity(intent)
    }
}
