package com.example.inflearncode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.inflearncode.level1.Level1Activity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goLevel1 = findViewById<Button>(R.id.goLevel1)

        goLevel1.setOnClickListener{
            val goLevel1Activity = Intent(this, Level1Activity::class.java)
            startActivity(goLevel1Activity)
        }


    }
}