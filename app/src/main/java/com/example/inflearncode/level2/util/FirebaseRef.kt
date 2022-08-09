package com.example.inflearncode.level2.util

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRef {

    companion object{

        private val database = Firebase.database


        val bookmarkRef = database.getReference("bookmark_list")

    }

}