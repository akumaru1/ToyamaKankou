package com.example.travelapp.Jp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.travelapp.DetailActivity
import com.example.travelapp.R
import com.example.travelapp.ROW_POSITION

class MainActivityJp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_jp)

        /* 画面移動のため */
        val btnKankouchi : ImageButton = findViewById(R.id.btnKankouchi)
        btnKankouchi.setOnClickListener {
            val intent = Intent(this, ChooseKankouchiJp::class.java)
            startActivity(intent)
        }

        val btnFood : ImageButton = findViewById(R.id.btnFood)
        btnFood.setOnClickListener {
            val intent = Intent(this, ChooseShokujiJp::class.java)
            startActivity(intent)
        }

        val btnManner : ImageButton = findViewById(R.id.btnManner)
        btnManner.setOnClickListener {
            val intent = Intent(this, MannerJp::class.java)
            startActivity(intent)
        }


        //for dont have choose
        val btnEvent: ImageButton = findViewById(R.id.btnEvent)
        btnEvent.setOnClickListener {
            val intent = Intent(this, EventJp::class.java)
            intent.putExtra("JSON_FILE_NAME", "eventjp.json")
            startActivity(intent)
        }

        //for dont have choose
        val btnBunka: ImageButton = findViewById(R.id.btnBunka)
        btnBunka.setOnClickListener {
            val intent = Intent(this, BunkaJp::class.java)
            intent.putExtra("JSON_FILE_NAME", "bunkajp.json")
            startActivity(intent)
        }





        //osusume
        val btnkansui: ImageButton = findViewById(R.id.btnkansui)
        btnkansui.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("JSON_FILE_NAME", "kankou2jp.json")
            intent.putExtra(ROW_POSITION, 0)
            startActivity(intent)
        }

        val btnAma: ImageButton = findViewById(R.id.btnAma)
        btnAma.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("JSON_FILE_NAME", "kankou1jp.json")
            intent.putExtra(ROW_POSITION, 0)
            startActivity(intent)
        }

        val btnkurobe: ImageButton = findViewById(R.id.btnkurobe)
        btnkurobe.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("JSON_FILE_NAME", "kankou3jp.json")
            intent.putExtra(ROW_POSITION, 4)
            startActivity(intent)
        }

        val btnkaze: ImageButton = findViewById(R.id.btnkaze)
        btnkaze.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("JSON_FILE_NAME", "eventjp.json")
            intent.putExtra(ROW_POSITION, 3)
            startActivity(intent)
        }

        val btnhotaru: ImageButton = findViewById(R.id.btnhotaru)
        btnhotaru.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("JSON_FILE_NAME", "eventjp.json")
            intent.putExtra(ROW_POSITION, 1)
            startActivity(intent)
        }

        val btnramen: ImageButton = findViewById(R.id.btnramen)
        btnramen.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("JSON_FILE_NAME", "gurume2jp.json")
            intent.putExtra(ROW_POSITION, 1)
            startActivity(intent)
        }

        val btnglass: ImageButton = findViewById(R.id.btnglass)
        btnglass.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("JSON_FILE_NAME", "bunkajp.json")
            intent.putExtra(ROW_POSITION, 3)
            startActivity(intent)
        }









    }
}
