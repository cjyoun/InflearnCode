package com.example.inflearncode.level1.mango

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.inflearncode.R

class MangoSplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mango_splash)


        Handler().postDelayed({
            startActivity(Intent(this,MangoActivity::class.java))
        }, 2000)

    }
}