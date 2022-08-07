package com.example.inflearncode.level2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.inflearncode.R
import com.example.inflearncode.level2.view.CommunityIntroActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CommunitySplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_splash)

        auth = Firebase.auth

        // uid 체크
        if(auth.currentUser?.uid == null){
            Log.d("SplashActivity,","uid null")
            // 2초뒤에 인트로 화면으로 이동
            Handler().postDelayed({
                startActivity(Intent(this,CommunityIntroActivity::class.java))
                finish()
            }, 2000)
        }else{
            Log.d("SplashActivity,", "uid not null")
            // 2초뒤에 메인 화면으로 이동 ( 로그인 한 사람이면 메인으로 이동 )
            Handler().postDelayed({
                startActivity(Intent(this,CommunityMainActivity::class.java))
                finish()
            }, 2000)
        }


    }
}