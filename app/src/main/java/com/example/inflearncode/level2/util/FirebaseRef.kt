package com.example.inflearncode.level2.util

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRef {

    companion object{

        private val database = Firebase.database

        val category1 = database.getReference("contents1")      // 카테고리1(ALL) 데이터 베이스
        val category2 = database.getReference("contents2")

        val bookmarkRef = database.getReference("bookmark_list")

    }

}