package com.example.inflearncode.level2.view.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.inflearncode.R
import com.example.inflearncode.databinding.ActivityCommunityBoardEditBinding
import com.example.inflearncode.level2.util.FBAuth
import com.example.inflearncode.level2.util.FBRef
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class CommunityBoardEditActivity : AppCompatActivity() {

    private lateinit var key: String
    private lateinit var binding: ActivityCommunityBoardEditBinding

    private val TAG = CommunityBoardEditActivity::class.java.simpleName

    private lateinit var writerUid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_community_board_edit)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_community_board_edit)

        // 게시글 key 값
        key = intent.getStringExtra("key").toString()

        getBoardData(key)       // 게시판 내용 가져오기
        getBoardImageData(key)  // 게시판 사진 가져오기

        binding.communityEditBtn.setOnClickListener {
            editBoardData(key)
        }

    }

    // 데이터 수정하기
    private fun editBoardData(key: String){
        FBRef.boardRef
            .child(key)
            .setValue(
                CommunityBoardModel(
                    binding.titleArea.text.toString(),
                    binding.contentArea.text.toString(),
                    writerUid,
                    FBAuth.getTime()
                )
            )

        Toast.makeText(this, "수정완료", Toast.LENGTH_LONG).show()
        finish()
    }

    private fun getBoardData(key: String){

        val postListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataModel = snapshot.getValue(CommunityBoardModel::class.java)

                // editTextView에는 .text = 으로 text를 보여주는게 아니라 setText를 해야함
                binding.titleArea.setText(dataModel?.title)
                binding.contentArea.setText(dataModel?.content)
                writerUid = dataModel!!.uid

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "loadPost:onCancelled", error.toException())
            }
        }

    }

    // 이미지 가져오기
    private fun getBoardImageData(key: String){
        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child("$key.png")

        // ImageView in your Activity
        val imageViewFromFB = binding.boardImgBtnArea

        storageReference.downloadUrl.addOnCompleteListener (OnCompleteListener{ task ->
            if(task.isSuccessful){
                Glide.with(this)
                    .load(task.result)
                    .into(imageViewFromFB)
            }else{

            }
        })

    }

}