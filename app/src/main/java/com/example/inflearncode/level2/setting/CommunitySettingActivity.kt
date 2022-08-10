package com.example.inflearncode.level2.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.inflearncode.R
import com.example.inflearncode.level2.view.CommunityIntroActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CommunitySettingActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_setting)

        val logoutBtn = findViewById<Button>(R.id.communityLogoutBtn)

        auth = Firebase.auth

        logoutBtn.setOnClickListener {
            auth.signOut()

            Toast.makeText(this, "로그아웃", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, CommunityIntroActivity::class.java)
            // 이전 Activity 삭제
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }
}