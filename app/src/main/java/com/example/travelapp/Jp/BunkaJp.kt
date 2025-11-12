package com.example.travelapp.Jp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travelapp.ListFragment
import com.example.travelapp.R
import com.example.travelapp.databinding.ActivityBunkaJpBinding
import com.example.travelapp.databinding.ActivityEventJpBinding

class BunkaJp : AppCompatActivity() {
    private lateinit var binding: ActivityBunkaJpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBunkaJpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jsonFileName = intent.getStringExtra("JSON_FILE_NAME") ?: "bunkajp.json"

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