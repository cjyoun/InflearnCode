package com.example.inflearncode.level2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.inflearncode.R
import com.example.inflearncode.level2.view.CommunityIntroActivity

class CommunitySplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_splash)

        // 2초뒤에 인트로 화면으로 이동
        Handler().postDelayed({
            startActivity(Intent(this,CommunityIntroActivity::class.java))
            finish()
        }, 2000)


    }
}