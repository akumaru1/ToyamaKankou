package com.example.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (savedInstanceState == null) {
            val jsonFileName = intent.getStringExtra("JSON_FILE_NAME")
            val position = intent.getIntExtra(ROW_POSITION, 0)

            if (jsonFileName != null) {
                val detailFragment = DetailFragment.newInstance(jsonFileName, position)

                supportFragmentManager.beginTransaction()
                    .replace(R.id.detail_container, detailFragment)
                    .commit()
            } else {
                // Handle the case where jsonFileName is null, e.g., show an error message or finish the activity.
                // Example: finish()
            }
        }
    }
}

