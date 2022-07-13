package com.example.inflearncode.level1.mango

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inflearncode.R
import com.example.inflearncode.level1.mango.model.ContentsModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MangoBookmartActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private val contentsModel = mutableListOf<ContentsModel>()  // 컨텐츠 모델 리스트

    override fun onCreate(savedInstanceState: Bundle?) {

        auth = Firebase.auth

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mango_bookmart)

        val database = Firebase.database
        val myBookmarkRef = database.getReference("bookmart_ref")

        // RecyclerView 만들기
        val recyclerView = findViewById<RecyclerView>(R.id.bookmarkRV)
        val rvAdapter = MangoRVAdapter(this, contentsModel)
        recyclerView.adapter = rvAdapter

        recyclerView.layoutManager = GridLayoutManager(this, 2)

        // DB에 저장된 값을 가지고 RecyclerView로 만들기
        myBookmarkRef
            .child(auth.currentUser?.uid.toString())
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    for(dataModel in snapshot.children){
                        Log.d("MangoBookmart", dataModel.toString())
                        contentsModel.add(dataModel.getValue(ContentsModel::class.java)!!)
                    }

                    rvAdapter.notifyDataSetChanged() // adapter를 새로 동기화 시키기

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("MangoBookmart", "dbError")
                }

            })



    }
}