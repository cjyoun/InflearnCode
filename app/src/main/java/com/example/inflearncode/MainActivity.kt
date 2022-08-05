package com.example.inflearncode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.inflearncode.level1.Level1Activity
import com.example.inflearncode.level2.CommunitySplashActivity

class MainActivity : AppCompatActivity() {

    private var isDouble = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goLevel1 = findViewById<Button>(R.id.goLevel1)
        goLevel1.setOnClickListener{
            val goLevel1Activity = Intent(this, Level1Activity::class.java)
            startActivity(goLevel1Activity)
        }


        val goLevel2 = findViewById<Button>(R.id.goLevel2)
        goLevel2.setOnClickListener{
            val goLevel2Activity = Intent(this, CommunitySplashActivity::class.java)
            startActivity(goLevel2Activity)
        }


    }

    // 뒤로가기 버튼 눌렀을 때
    override fun onBackPressed() {
        Log.d("MainActivity", "뒤로가기버튼")
        Toast.makeText(this,"종료하시려면 더블 클릭 하세요.", Toast.LENGTH_SHORT).show()
        // isDouble값이 true이면 앱 종료
        if(isDouble){
            finish()
        }
        isDouble = true


        // 뒤로가기 버튼 눌리고 2초뒤에 isDouble 값 false 로 변환
        Handler().postDelayed({
            isDouble=false
        },2000)
    }

}