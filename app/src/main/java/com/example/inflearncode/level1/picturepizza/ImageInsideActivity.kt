package com.example.inflearncode.level1.picturepizza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.inflearncode.R

class ImageInsideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_inside)

        val getData = intent.getStringExtra("data")
        Toast.makeText(this,"pizza$getData", Toast.LENGTH_SHORT).show()

        val pizzaPicture = findViewById<ImageView>(R.id.pizzaArea)

        when (getData){
            "1" -> pizzaPicture.setImageResource(R.drawable.pizza1)    // pizzaArea 의 값의 사진을 변경
            "2" -> pizzaPicture.setImageResource(R.drawable.pizza2)
            "3" -> pizzaPicture.setImageResource(R.drawable.pizza3)
            "4" -> pizzaPicture.setImageResource(R.drawable.pizza4)
            "5" -> pizzaPicture.setImageResource(R.drawable.pizza5)
            else -> Toast.makeText(this,"없는 사진 번호 입니다.", Toast.LENGTH_SHORT).show()
        }


    }
}