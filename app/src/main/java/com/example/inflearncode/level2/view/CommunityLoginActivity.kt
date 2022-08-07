package com.example.inflearncode.level2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.inflearncode.R
import com.example.inflearncode.databinding.ActivityCommunityLoginBinding
import com.example.inflearncode.level2.CommunityMainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CommunityLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommunityLoginBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_community_login)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_community_login)

        auth = Firebase.auth

        // 로그인 버튼 누르기
        binding.communityLoginBtn.setOnClickListener {

            var isGoToLogin = true

            val email = binding.communityLoginEmail.text.toString()
            val password = binding.communityLoginPassword.text.toString()

            // 값이 비어있는지 확인
            if(email.isEmpty()){
                Toast.makeText(this,"이메일을 입력해 주세요.",Toast.LENGTH_LONG).show()
                isGoToLogin = false
            }
            if(password.isEmpty()){
                Toast.makeText(this,"Password를 입력해 주세요.",Toast.LENGTH_LONG).show()
                isGoToLogin = false
            }

            if(isGoToLogin){
                // firebase 기존 사용자 로그인
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()

                            val intent = Intent(this, CommunityMainActivity::class.java)
                            // 페이지 이동 시 이전의 activity 들을 지워버림 -> 이동 후 뒤로가기 누르면 앱이 꺼짐
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                            startActivity(intent)

                        } else {

                            // todo. firebase 에서 내려주는 error 코드 찾아내서 각 부문별로 exceptionMessage 작성
                            var exceptionMessage = ""
                            val taskException = task.exception?.message.toString()
                            if (taskException == "The email address is badly formatted.") {
                                exceptionMessage = "존재하지 않는 아이디 입니다."
                            }
                            Toast.makeText(this, "로그인 실패 - $exceptionMessage", Toast.LENGTH_LONG).show()
                        }
                    }

            }

        }

    }
}