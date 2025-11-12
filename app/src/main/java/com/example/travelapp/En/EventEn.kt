package com.example.travelapp.En

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travelapp.ListFragment
import com.example.travelapp.R
import com.example.travelapp.databinding.ActivityEventJpBinding

class EventEn : AppCompatActivity() {
    private lateinit var binding: ActivityEventJpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventJpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jsonFileName = intent.getStringExtra("JSON_FILE_NAME") ?: "eventen.json"

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