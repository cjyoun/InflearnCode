package com.example.inflearncode.level1.picturepizza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.inflearncode.R

class PizzaPictureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_picture)

        val btn1 = findViewById<ImageView>(R.id.pizza1)
        val btn2 = findViewById<ImageView>(R.id.pizza2)
        val btn3 = findViewById<ImageView>(R.id.pizza3)
        val btn4 = findViewById<ImageView>(R.id.pizza4)
        val btn5 = findViewById<ImageView>(R.id.pizza5)

        btn1.setOnClickListener {
            val intent = Intent(this, ImageInsideActivity::class.java)
            intent.putExtra("data","1")
            startActivity(intent)
        }

        btn2.setOnClickListener {
            val intent = Intent(this, ImageInsideActivity::class.java)
            intent.putExtra("data","2")
            startActivity(intent)
        }

        btn3.setOnClickListener {
            val intent = Intent(this, ImageInsideActivity::class.java)
            intent.putExtra("data","3")
            startActivity(intent)
        }

        btn4.setOnClickListener {
            val intent = Intent(this, ImageInsideActivity::class.java)
            intent.putExtra("data","4")
            startActivity(intent)
        }

        btn5.setOnClickListener {
            val intent = Intent(this, ImageInsideActivity::class.java)
            intent.putExtra("data", "5")
            startActivity(intent)
        }

    }
}