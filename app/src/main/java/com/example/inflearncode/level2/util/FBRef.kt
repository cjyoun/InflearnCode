package com.example.inflearncode.level2.util

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FBRef {

    companion object{

        private val database = Firebase.database

        // path 로 되어 있는 부분이 firebase DB에 저장할 DB명

        val category1 = database.getReference("contents1")      // 카테고리1(ALL) 데이터 베이스
        val category2 = database.getReference("contents2")      // 카테고리2 DB 가져오기

        val bookmarkRef = database.getReference("bookmark_list")    // 북마크 리스트 DB

        val boardRef = database.getReference("bard")    // 게시판 DB

    }

}