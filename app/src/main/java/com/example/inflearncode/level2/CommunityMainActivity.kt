package com.example.inflearncode.level2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.inflearncode.R
import com.example.inflearncode.level2.setting.CommunitySettingActivity
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


        findViewById<ImageView>(R.id.communityLogoutBtn).setOnClickListener {
            val intent = Intent(this, CommunitySettingActivity::class.java)
            startActivity(intent)
        }

    }
}