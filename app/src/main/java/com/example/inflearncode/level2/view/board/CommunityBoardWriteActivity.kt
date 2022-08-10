package com.example.inflearncode.level2.view.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.inflearncode.R
import com.example.inflearncode.databinding.ActivityCommunityBoardWriteBinding
import com.example.inflearncode.level2.util.FirebaseAuth
import com.example.inflearncode.level2.util.FirebaseRef

class CommunityBoardWriteActivity : AppCompatActivity() {

    private val TAG = CommunityBoardWriteActivity::class.java.simpleName
    private lateinit var binding : ActivityCommunityBoardWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_community_board_write)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_community_board_write)

        // 입력 버튼 눌렀을 때
        binding.communityWriteBtn.setOnClickListener {

            val title = binding.titleArea.text.toString()
            val content = binding.contentArea.text.toString()
            val uid = FirebaseAuth.getUid()
            val time = FirebaseAuth.getTime()

            FirebaseRef.boardRef
                .push() // 랜덤한 키값으로 push 됨
                .setValue(CommunityBoardModel(title,content,uid,time))

            Toast.makeText(this,"게시글 등록 완료",Toast.LENGTH_SHORT).show()

            finish() // 글 등록 후 Activity 끄기

        }


    }
}