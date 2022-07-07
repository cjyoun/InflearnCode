package com.example.inflearncode.level1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.inflearncode.R
import com.example.inflearncode.level1.dice.DiceActivity
import com.example.inflearncode.level1.picturechicken.ChickenPictureActivity
import com.example.inflearncode.level1.picturepizza.PizzaPictureActivity

class Level1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level1)

        // 치킨 사진 보기 페이지 이동
        val goChickenPictureBtn = findViewById<Button>(R.id.goChickenPicture)
        goChickenPictureBtn.setOnClickListener{
            val goChickenPictureActivity = Intent(this, ChickenPictureActivity::class.java)
            startActivity(goChickenPictureActivity)
        }

        // 피자 사진 보기 페이지 이동
        val goPizzaPictureBtn = findViewById<Button>(R.id.goPizzaPicture)
        goPizzaPictureBtn.setOnClickListener{
            val goPizzaPictureActivity = Intent(this, PizzaPictureActivity::class.java)
            startActivity(goPizzaPictureActivity)
        }

        // 주사위 돌리기 페이지 이동
        val goDiceBtn = findViewById<Button>(R.id.goDice)
        goDiceBtn.setOnClickListener{
            val goDiceActivity = Intent(this, DiceActivity::class.java)
            startActivity(goDiceActivity)
        }
    }
}