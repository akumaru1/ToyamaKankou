package com.example.travelapp.En

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.travelapp.DetailActivity
import com.example.travelapp.Jp.BunkaJp
import com.example.travelapp.Jp.EventJp
import com.example.travelapp.Jp.MannerJp
import com.example.travelapp.R
import com.example.travelapp.ROW_POSITION
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityEn : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_en)

        /* 画面移動のため */
        val btnKankouchi : ImageButton = findViewById(R.id.btnKankouchi)
        btnKankouchi.setOnClickListener {
            val intent = Intent(this, ChooseKankouchiEn::class.java)
            startActivity(intent)
        }

        val btnFood : ImageButton = findViewById(R.id.btnFood)
        btnFood.setOnClickListener {
            val intent = Intent(this, ChooseShokujiEn::class.java)
            startActivity(intent)
        }

        val btnManner : ImageButton = findViewById(R.id.btnManner)
        btnManner.setOnClickListener {
            val intent = Intent(this, MannerEn::class.java)
            startActivity(intent)
        }

        //for dont have choose
        val btnEvent: ImageButton = findViewById(R.id.btnEvent)
        btnEvent.setOnClickListener {
            val intent = Intent(this, EventJp::class.java)
            intent.putExtra("JSON_FILE_NAME", "eventen.json")
            startActivity(intent)
        }

        //for dont have choose
        val btnBunka: ImageButton = findViewById(R.id.btnBunka)
        btnBunka.setOnClickListener {
            val intent = Intent(this, BunkaJp::class.java)
            intent.putExtra("JSON_FILE_NAME", "bunkaen.json")
            startActivity(intent)
        }







        //osusume
        val btnkansui: ImageButton = findViewById(R.id.btnkansui)
        btnkansui.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("JSON_FILE_NAME", "kankou2en.json")
            intent.putExtra(ROW_POSITION, 0)
            startActivity(intent)
        }

        val btnAma: ImageButton = findViewById(R.id.btnAma)
        btnAma.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("JSON_FILE_NAME", "kankou1en.json")
            intent.putExtra(ROW_POSITION, 0)
            startActivity(intent)
        }

        val btnkurobe: ImageButton = findViewById(R.id.btnkurobe)
        btnkurobe.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("JSON_FILE_NAME", "kankou3en.json")
            intent.putExtra(ROW_POSITION, 4)
            startActivity(intent)
        }

        val btnkaze: ImageButton = findViewById(R.id.btnkaze)
        btnkaze.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("JSON_FILE_NAME", "eventen.json")
            intent.putExtra(ROW_POSITION, 3)
            startActivity(intent)
        }

        val btnhotaru: ImageButton = findViewById(R.id.btnhotaru)
        btnhotaru.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("JSON_FILE_NAME", "eventen.json")
            intent.putExtra(ROW_POSITION, 1)
            startActivity(intent)
        }
        val btnramen: ImageButton = findViewById(R.id.btnramen)
        btnramen.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("JSON_FILE_NAME", "gurume2en.json")
            intent.putExtra(ROW_POSITION, 1)
            startActivity(intent)
        }

        val btnglass: ImageButton = findViewById(R.id.btnglass)
        btnglass.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("JSON_FILE_NAME", "bunkaen.json")
            intent.putExtra(ROW_POSITION, 3)
            startActivity(intent)
        }





    }
}
