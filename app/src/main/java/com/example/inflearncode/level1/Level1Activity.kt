package com.example.inflearncode.level1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.inflearncode.R
import com.example.inflearncode.level1.picturechicken.ChickenPictureActivity

class Level1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level1)

        val goChickenPictureBtn = findViewById<Button>(R.id.goChickenPicture)
        goChickenPictureBtn.setOnClickListener{
            val goChickenPictureActivity = Intent(this, ChickenPictureActivity::class.java)
            startActivity(goChickenPictureActivity)
        }
    }
}