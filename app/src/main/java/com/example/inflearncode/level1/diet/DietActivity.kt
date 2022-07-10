package com.example.inflearncode.level1.diet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.inflearncode.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DietActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth // firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val noLoginBtn = findViewById<Button>(R.id.noIdLogin)
        noLoginBtn.setOnClickListener {

            // firebase 익명 로그인
            auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    // 성공
                    if (task.isSuccessful) {

                        val user = auth.currentUser
                        Log.d("DietActivity", "유저 UID - ${user!!.uid}")

                    } else {    // 실패
                        Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                }

        }


    }
}