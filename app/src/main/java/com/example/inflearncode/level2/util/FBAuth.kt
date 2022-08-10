package com.example.inflearncode.level2.util

import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

class FBAuth {

    companion object{
        private lateinit var auth: FirebaseAuth

        // 핸드폰의 고유 아이디 가져오기
        fun getUid() : String{

            auth = FirebaseAuth.getInstance()

            return auth.currentUser?.uid.toString()

        }

        // 시간 가져오기
        fun getTime() : String{
            val currentDateTime = Calendar.getInstance().time   // 현재 시간을 time 형태로 지정
            val dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:SS", Locale.KOREA).format(currentDateTime)  // time형태의 현재 시간을 포멧에 맞춰서 출력
            return dateFormat.toString()
        }

    }
}