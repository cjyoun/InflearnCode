package com.example.inflearncode.level2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.inflearncode.R
import com.example.inflearncode.level2.view.CommunityIntroActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CommunityMainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_main)

        auth = Firebase.auth

//        findViewById<Button>(R.id.communityLogoutBtn).setOnClickListener {
//
//            auth.signOut()  // firebase 로그아웃 하기
//
//            val intent = Intent(this, CommunityIntroActivity::class.java)
//            // 페이지 이동 시 이전의 activity 들을 지워버림 -> 이동 후 뒤로가기 누르면 앱이 꺼짐
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
//            startActivity(intent)
//        }

    }
}