package com.example.inflearncode.level1.mango

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.inflearncode.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MangoJoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mango_join)

        val joinBtn = findViewById<Button>(R.id.mangoJoinBtn)

        auth = Firebase.auth

        joinBtn.setOnClickListener {
            val email = findViewById<EditText>(R.id.mangoId)
            val password = findViewById<EditText>(R.id.mangoPw)

            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "이메일 인증 성공 ", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MangoActivity::class.java))

                    } else {
                        Toast.makeText(this, "이메일 인증 실패 ", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}