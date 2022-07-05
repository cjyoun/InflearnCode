package com.example.inflearncode.level1.picturechicken

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.inflearncode.R

class ChickenPictureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val image1 = findViewById<ImageView>(R.id.chickenImage1)
        val image2 = findViewById<ImageView>(R.id.chickenImage2)
        val image3 = findViewById<ImageView>(R.id.chickenImage3)
        val image4 = findViewById<ImageView>(R.id.chickenImage4)
        val image5 = findViewById<ImageView>(R.id.chickenImage5)

        image1.setOnClickListener {
            Toast.makeText(this,"1번 치킨",Toast.LENGTH_SHORT).show()
            var intent = Intent(this, Chicken1Activity::class.java)
            startActivity(intent)
        }

        image2.setOnClickListener {
            Toast.makeText(this,"2번 치킨",Toast.LENGTH_SHORT).show()
            var intent = Intent(this, Chicken2Activity::class.java)
            startActivity(intent)
        }

        image3.setOnClickListener {
            Toast.makeText(this,"3번 치킨",Toast.LENGTH_SHORT).show()
            var intent = Intent(this, Chicken3Activity::class.java)
            startActivity(intent)
        }

        image4.setOnClickListener {
            Toast.makeText(this,"4번 치킨",Toast.LENGTH_SHORT).show()
            var intent = Intent(this, Chicken4Activity::class.java)
            startActivity(intent)
        }

        image5.setOnClickListener {
            Toast.makeText(this,"5번 치킨",Toast.LENGTH_SHORT).show()
            var intent = Intent(this, Chicken5Activity::class.java)
            startActivity(intent)
        }

    }
}