package com.example.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travelapp.databinding.ActivityKankouchiBinding

class Kankouchi : AppCompatActivity() {

    private lateinit var binding: ActivityKankouchiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKankouchiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jsonFileName = intent.getStringExtra("JSON_FILE_NAME") ?: "kankou1jp.json"

        val tag = "ListFragment"
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            fragment = ListFragment.newInstance(jsonFileName)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.content, fragment, tag)
            }.commit()
        }
    }
}
