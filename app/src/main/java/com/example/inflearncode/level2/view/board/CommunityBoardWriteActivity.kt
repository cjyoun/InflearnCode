package com.example.inflearncode.level2.view.board

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.inflearncode.R
import com.example.inflearncode.databinding.ActivityCommunityBoardWriteBinding
import com.example.inflearncode.level2.util.FBAuth
import com.example.inflearncode.level2.util.FBRef
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class CommunityBoardWriteActivity : AppCompatActivity() {

    private val TAG = CommunityBoardWriteActivity::class.java.simpleName
    private lateinit var binding : ActivityCommunityBoardWriteBinding

    private var isImageUpload = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_community_board_write)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_community_board_write)

        // 입력 버튼 눌렀을 때
        binding.communityWriteBtn.setOnClickListener {

            val title = binding.titleArea.text.toString()
            val content = binding.contentArea.text.toString()
            val uid = FBAuth.getUid()
            val time = FBAuth.getTime()

            // firebase storage에 이미지를 저장하려고 할 때
            // 게시글을 선택하면 그 게시글의 데이터를 가져오는 것 처럼 이미지도 가져오기 위해서
            // 게시글을 저장할 때 생성되는 key 값을 가지고 있고 image 생성시 그 키값을 파일명으로 해서 저장
            // (이미지 이름을 모르기 때문에 key값으로 저장시키는 것)

            // 랜덤한 키값
            val key = FBRef.boardRef.push().key.toString()

            // firebase에 게시글 데이터 저장 -> 키값으로 push 됨
            FBRef.boardRef
                .child(key)
                .setValue(CommunityBoardModel(title,content,uid,time))

            Toast.makeText(this,"게시글 등록 완료",Toast.LENGTH_SHORT).show()

            // 이미지 추가 버튼을 눌렀을 경우에만 실행
            if(isImageUpload){
                imageUpload(key) // firebase storage에 이미지 업로드 하기
            }

            finish() // 글 등록 후 Activity 끄기

        }


        // 사진 추가 버튼 클릭 시
        binding.boardImgBtnArea.setOnClickListener {
            isImageUpload = true
            // 갤러리 사진 열기
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)

        }


    }

    // startActivityForResult 함수 호출 후 결과 내려오는 부분
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK && requestCode ==100){
            binding.boardImgBtnArea.setImageURI(data?.data)    // 갤러리 선택한 이미지 넣기
        }
    }

    // firebase에 image 업로드하기 https://firebase.google.com/docs/storage/android/upload-files?hl=ko
    private fun imageUpload(key:String){
        val storage = Firebase.storage // firebase storage

        // Create a storage reference from our app
        val storageRef = storage.reference

        // 게시글의 키값과 같은 이름으로 사진파일명 지정
        val mountainsRef = storageRef.child("$key.png")

        // Get the data from an ImageView as bytes
        val imageView = binding.boardImgBtnArea
        imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache()
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }


}