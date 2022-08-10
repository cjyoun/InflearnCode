package com.example.inflearncode.level2.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.example.inflearncode.R


class CommunityStoreFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_community_store, container, false)

        val webView: WebView = view.findViewById(R.id.contentWebView)

        webView.loadUrl("https://blog.naver.com/cjyoun95")

        return view
    }

}