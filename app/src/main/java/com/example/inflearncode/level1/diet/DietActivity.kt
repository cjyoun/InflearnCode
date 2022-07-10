package com.example.inflearncode.level1.diet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
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

            // firebase 익명 로그인  (https://firebase.google.com/docs/auth/android/anonymous-auth?hl=ko  -> firebase Document)
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


        // 이메일로 로그인 (https://firebase.google.com/docs/auth/android/password-auth?hl=ko  -> firebase Document)
        val joinBtn = findViewById<Button>(R.id.joinBtn)
        joinBtn.setOnClickListener {
            val email = findViewById<EditText>(R.id.emailArea)
            val password = findViewById<EditText>(R.id.passwordArea)

            Log.d("DietActivity", "이메일 - $email  / 비밀번호 - $password")


            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "이메일 인증 성공 ",Toast.LENGTH_SHORT).show()
                        val user = auth.currentUser

                    } else {
                        Toast.makeText(this, "이메일 인증 실패 ",Toast.LENGTH_SHORT).show()
                    }
                }

        }

        // 로그아웃 버튼
        val logoutBtn = findViewById<Button>(R.id.logoutBtn)
        logoutBtn.setOnClickListener {
            Firebase.auth.signOut() // 로그아웃 하기
            Toast.makeText(this, "로그아웃 ",Toast.LENGTH_SHORT).show()
        }

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        loginBtn.setOnClickListener {
            val email2 = findViewById<EditText>(R.id.emailArea)
            val password2 = findViewById<EditText>(R.id.passwordArea)

            auth.signInWithEmailAndPassword(email2.text.toString(), password2.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "로그인 성공 ",Toast.LENGTH_SHORT).show()
                        val user = auth.currentUser

                    } else {
                        Toast.makeText(this, "로그인 실패 ",Toast.LENGTH_SHORT).show()
                    }
                }
        }


    }
}