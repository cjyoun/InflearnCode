package com.example.inflearncode.level2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.inflearncode.R
import com.example.inflearncode.databinding.ActivityCommunityIntroBinding

class CommunityIntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommunityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_community_intro)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_community_intro)

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

//        binding.communityGoNoAccountBtn.setOnClickListener {
//            val intent = Intent(this,CommunityNoActivity::class.java)
//            startActivity(intent)
//        }

    }
}