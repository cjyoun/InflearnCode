package com.example.inflearncode.level1.mango

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.TextView
import com.example.inflearncode.R
import com.example.inflearncode.level1.mango.model.ContentsModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MangoWebViewActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mango_web_view)

        val url = intent.getStringExtra("url").toString()   // 인텐트로 받아온 url 값
        val imgUrl = intent.getStringExtra("imgUrl").toString()   // 인텐트로 받아온 imgUrl 값
        val title = intent.getStringExtra("title").toString()   // 인텐트로 받아온 titleText 값

        // 웹뷰 보여주기
        val webView = findViewById<WebView>(R.id.mangoWebView)
        webView.loadUrl(url)

        val database = Firebase.database
        val myBookmarkRef = database.getReference("bookmart_ref")

        auth = Firebase.auth

        // 저장 버튼으로 db에 저장하기
        val saveText = findViewById<TextView>(R.id.saveText)
        saveText.setOnClickListener {
            // 접속한 사람의 uid로 ContentsModel값 db에 넣기 (선택된 url에 대한 값을 저장)
            myBookmarkRef
                .child(auth.currentUser!!.uid)
                .push()
                .setValue(ContentsModel(url, imgUrl, title))
        }



    }
}