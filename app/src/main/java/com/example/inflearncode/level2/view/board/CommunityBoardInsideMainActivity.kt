package com.example.inflearncode.level2.view.board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.inflearncode.R
import com.example.inflearncode.databinding.ActivityCommunityBoardInsideMainBinding
import com.example.inflearncode.level2.util.FBRef
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.lang.Exception

class CommunityBoardInsideMainActivity : AppCompatActivity() {

    private val TAG = CommunityBoardInsideMainActivity::class.java.simpleName
    private lateinit var binding : ActivityCommunityBoardInsideMainBinding

    private var key = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_community_board_inside_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_community_board_inside_main)

        // 첫번째 방법 - Intent로 데이터들 다 넘겨서 이동된 Activity에서 보여주기
//        val title = intent.getStringExtra("title")
//        val content = intent.getStringExtra("content")
//        val time = intent.getStringExtra("time")
//
//        binding.boardTitleArea.text = title
//        binding.boardContentArea.text = content
//        binding.boardTimeArea.text = time


        // 두번째 방법 - id값을 가지고 firebase에서 가져오기
        key = intent.getStringExtra("key").toString()
        getBoardData(key)

        // 이미지 가져오기
        getBoardImageData(key)


        // 보드세팅 이미지 클릭 시 다이얼로그 띄우기
        binding.boardSettingIcon.setOnClickListener {
            Log.d(TAG,"dkdkdk?")
            showDialog()
        }


    }

    private fun getBoardData(key: String){

        val postListener = object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                try{

                    val dataModel = snapshot.getValue(CommunityBoardModel::class.java)
                    Log.d(TAG, dataModel!!.toString())

                    // null 값이 없다는 의미로 !! 붙이기  -> null 체크는 해주는 것 이 좋음
                    binding.boardTitleArea.text = dataModel!!.title
                    binding.boardContentArea.text = dataModel!!.content
                    binding.boardTimeArea.text = dataModel!!.time

                } catch (e:Exception){
                    Log.d(TAG, "삭제완료 - 삭제로 인한 exception발생")
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "loadPost:onCancelled", error.toException())
            }
        }
        FBRef.boardRef.child(key).addValueEventListener(postListener)

    }


    // 이미지 가져오기
    private fun getBoardImageData(key: String){
        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child("$key.png")

        // ImageView in your Activity
        val imageViewFromFB = binding.boardImgArea

        storageReference.downloadUrl.addOnCompleteListener (OnCompleteListener{ task ->
            if(task.isSuccessful){
                Glide.with(this)
                    .load(task.result)
                    .into(imageViewFromFB)
            }else{

            }
        })

    }


    private fun showDialog(){

        val mDialogView = LayoutInflater.from(this).inflate(R.layout.community_custom_dialog, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("게시글 수정/삭제")

        val alertDialog = mBuilder.show()

        // 수정버튼
        alertDialog.findViewById<Button>(R.id.boardReviseBtn)?.setOnClickListener {
            Toast.makeText(this,"수정버튼 누름",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CommunityBoardEditActivity::class.java)
            intent.putExtra("key",key)
            startActivity(intent)
        }

        // 삭제버튼
        alertDialog.findViewById<Button>(R.id.boardRemoveBtn)?.setOnClickListener {
            FBRef.boardRef.child(key).removeValue()
            Toast.makeText(this,"삭제",Toast.LENGTH_SHORT).show()
            finish()
        }
    }

}