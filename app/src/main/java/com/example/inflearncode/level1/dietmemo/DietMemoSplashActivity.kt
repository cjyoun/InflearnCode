package com.example.inflearncode.level1.dietmemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.inflearncode.MainActivity
import com.example.inflearncode.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class DietMemoSplashActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_memo_splash)

        auth = Firebase.auth

        try{
            Log.d("DietMemoSplash", "원래 비회원 로그인이 되어있는 사람 - ${auth.currentUser!!.uid} ")

            // 핸들러를 사용하여 2초뒤에 MainActivity로 이동
            Handler().postDelayed({
                startActivity(Intent(this, DietMemoActivity::class.java))
                finish()
            }, 2000)

        }catch (e:Exception){
            Log.d("DietMemoSplash", "회원가입 시켜줘야함 ")

            auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        Log.d("DietMemoSplash", "메모앱 스플래쉬화면 익명로그인 성공 - ${user!!.uid}")
                        Handler().postDelayed({
                            startActivity(Intent(this, DietMemoActivity::class.java))
                            finish()
                        }, 2000)
                    } else {
                        Log.d("DietMemoSplash", "메모앱 스플래쉬화면 익명로그인 실패 ")

                    }
                }
        }







    }
}