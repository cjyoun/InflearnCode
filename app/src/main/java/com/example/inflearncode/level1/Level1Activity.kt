package com.example.inflearncode.level1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.inflearncode.R
import com.example.inflearncode.level1.dice.DiceActivity
import com.example.inflearncode.level1.dietmemo.DietMemoSplashActivity
import com.example.inflearncode.level1.firebasepractice.FirebasePracticeActivity
import com.example.inflearncode.level1.mango.MangoSplashActivity
import com.example.inflearncode.level1.musiclist.MusicActivity
import com.example.inflearncode.level1.picturechicken.ChickenPictureActivity
import com.example.inflearncode.level1.picturepizza.PizzaPictureActivity
import com.example.inflearncode.level1.wisesaying.WiseSayingActivity

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

        // 명언 보기 페이지 이동
        val goWiseSayingBtn = findViewById<Button>(R.id.goWiseSaying)
        goWiseSayingBtn.setOnClickListener{
            val goWiseSayingActivity = Intent(this, WiseSayingActivity::class.java)
            startActivity(goWiseSayingActivity)
        }

        // 음악리스트 보기 페이지 이동
        val goMusicListBtn = findViewById<Button>(R.id.goMusicList)
        goMusicListBtn.setOnClickListener{
            val goMusicListActivity = Intent(this, MusicActivity::class.java)
            startActivity(goMusicListActivity)
        }


        // firebase연습 페이지 이동
        val goFirebasePracticeBtn = findViewById<Button>(R.id.goFirebasePractice)
        goFirebasePracticeBtn.setOnClickListener{
            val goFirebasePracticeActivity = Intent(this, FirebasePracticeActivity::class.java)
            startActivity(goFirebasePracticeActivity)
        }

        // 다이어트 앱  페이지 이동
        val goDietBtn = findViewById<Button>(R.id.goDiet)
        goDietBtn.setOnClickListener{
            val goDietActivity = Intent(this, DietMemoSplashActivity::class.java)
            startActivity(goDietActivity)
        }

        // 망고 플레이트  페이지 이동
        val goMangoBtn = findViewById<Button>(R.id.goMango)
        goMangoBtn.setOnClickListener{
            val goMangoActivity = Intent(this, MangoSplashActivity::class.java)
            startActivity(goMangoActivity)
        }


    }
}