package com.example.inflearncode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.Toast
import com.example.inflearncode.level1.Level1Activity

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


    }

    // 뒤로가기 버튼 눌렀을 때
    override fun onBackPressed() {
        // isDouble값이 true이면 앱 종료
        if(isDouble){
            finish()
        }
        isDouble = true
        Toast.makeText(this,"종료하시려면 더블 클릭 하세요.", Toast.LENGTH_SHORT).show()

        // 뒤로가기 버튼 눌리고 2초뒤에 isDouble 값 false 로 변환
        Handler().postDelayed({
            isDouble=false
        },2000)
    }

}