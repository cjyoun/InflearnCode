package com.example.inflearncode.level1.mango

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.inflearncode.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MangoSplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mango_splash)

        auth = Firebase.auth

        if(auth.currentUser?.uid == null){
            // 회원가입이 안되어 있으므로 회원가입 페이지로 이동
            Handler().postDelayed({
                startActivity(Intent(this,MangoJoinActivity::class.java))
            }, 2000)

        }else{
            // 회원가입이 되어 있으므로 메인 페이지로 이동
            Handler().postDelayed({
                startActivity(Intent(this,MangoActivity::class.java))
            }, 2000)
        }




    }
}