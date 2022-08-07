package com.example.inflearncode.level2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.inflearncode.R
import com.example.inflearncode.databinding.ActivityCommunityIntroBinding
import com.example.inflearncode.level2.CommunityMainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CommunityIntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommunityIntroBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_community_intro)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_community_intro)

        auth = Firebase.auth

        // 로그인 버튼
        binding.communityGoLoginBtn.setOnClickListener {
            val intent = Intent(this,CommunityLoginActivity::class.java)
            startActivity(intent)
        }

        // 회원가입 버튼
        binding.communityGoJoinBtn.setOnClickListener {
            val intent = Intent(this,CommunityJoinActivity::class.java)
            startActivity(intent)
        }

        binding.communityGoNoAccountBtn.setOnClickListener {

            auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this,"비회원 로그인 성공", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, CommunityMainActivity::class.java)
                        // 페이지 이동 시 이전의 activity 들을 지워버림 -> 이동 후 뒤로가기 누르면 앱이 꺼짐
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                    } else {
                        Toast.makeText(this,"비회원 로그인 실패", Toast.LENGTH_LONG).show()
                    }
                }


        }

    }
}