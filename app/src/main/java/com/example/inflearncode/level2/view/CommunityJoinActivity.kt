package com.example.inflearncode.level2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.inflearncode.R
import com.example.inflearncode.databinding.ActivityCommunityJoinBinding
import com.example.inflearncode.level2.CommunityMainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CommunityJoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityCommunityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_community_join)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_community_join)

        auth = Firebase.auth

        // 회원가입 버튼 클릭
        binding.communityJoinBtn.setOnClickListener {

            var isGoToJoin = true   // 조건을 만족하는지 여부 체크

            val joinEmail = binding.communityJoinEmail.text.toString()
            val joinPassword = binding.communityJoinPassword.text.toString()
            val joinPassword2 = binding.communityJoinPassword2.text.toString()

            // 값이 비어있는지 확인
            if(joinEmail.isEmpty()){
                Toast.makeText(this,"이메일을 입력해 주세요.",Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }
            if(joinPassword.isEmpty()){
                Toast.makeText(this,"Password를 입력해 주세요.",Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }
            if(joinPassword2.isEmpty()){
                Toast.makeText(this,"Password check를 입력해 주세요.",Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            // 비밀번호 두개가 같은지 확인
            if(!joinPassword.equals(joinPassword2) ){
                Toast.makeText(this,"비밀번호를 똑같이 입력해 주세요.",Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            // 비밀번호 길이 정하기
            if(joinPassword.length<6){
                Toast.makeText(this,"Password를 6자리 이상으로 입력해 주세요.",Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }


            if(isGoToJoin){
                // firebase 이메일 회원가입
                auth.createUserWithEmailAndPassword(joinEmail, joinPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this,"회원가입 성공",Toast.LENGTH_LONG).show()

                            val intent = Intent(this, CommunityMainActivity::class.java)
                            // 페이지 이동 시 이전의 activity 들을 지워버림 -> 이동 후 뒤로가기 누르면 앱이 꺼짐
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                            startActivity(intent)

                        } else {
                            var exceptionMessage = ""
                            val taskException = task.exception?.message.toString()
                            if(taskException=="The email address is badly formatted."){
                                exceptionMessage = "이메일 형식이 올바르지 않습니다."
                            }
                            Toast.makeText(this,"회원가입 실패 - $exceptionMessage",Toast.LENGTH_LONG).show()
                        }
                    }
            }



        }






    }


}