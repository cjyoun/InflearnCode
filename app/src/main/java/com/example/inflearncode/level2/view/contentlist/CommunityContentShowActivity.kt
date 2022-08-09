package com.example.inflearncode.level2.view.contentlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.inflearncode.R

class CommunityContentShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_content_show)

        val webUrl = intent.getStringExtra("url")

        val webView: WebView = findViewById(R.id.contentWebView)

        webView.loadUrl(webUrl.toString())

    }
}