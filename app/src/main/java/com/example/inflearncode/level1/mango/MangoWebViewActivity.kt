package com.example.inflearncode.level1.mango

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.inflearncode.R

class MangoWebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mango_web_view)

        val url = intent.getStringExtra("url").toString()   // 인텐트로 받아온 url 값

        // 웹뷰 보여주기
        val webView = findViewById<WebView>(R.id.mangoWebView)
        webView.loadUrl(url)


    }
}