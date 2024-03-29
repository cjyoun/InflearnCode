package com.example.inflearncode.level2.view.contentlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inflearncode.R
import com.example.inflearncode.level2.util.FBAuth
import com.example.inflearncode.level2.util.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CommunityContentListActivity : AppCompatActivity() {

    lateinit var rvAdapter : CommunityContentRVAdapter

    private lateinit var myRef: DatabaseReference   // firebase DB

    val bookmarkIdList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_content_list)

        // firebase database 사용
        val database = Firebase.database


        // intent로 넘겨받은 카테고리 번호
        val categoryNum = intent.getStringExtra("category")

        // 카테고리 별로 firebase에 있는 db가져오기
        if(categoryNum=="category1"){
            myRef = database.getReference("contents1")
        } else if(categoryNum=="category2"){
            myRef = database.getReference("contents2")
        }

        val items = ArrayList<CommunityContentModel>()  // 아이템 리스트 (RecyclerView 에 보내서 뿌려줄 리스트)
        val itemKeyList = ArrayList<String>()   // 아이템 별 key 값 리스트 (uuid)
        rvAdapter = CommunityContentRVAdapter(baseContext, items, itemKeyList, bookmarkIdList)

        // firebaes database 값들 가져오기
        // ------------------------------------------------------
        val postListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                for(dataModel in snapshot.children){
                    Log.d("dataModel", dataModel.toString())    // item 데이터 리스트
                    val item = dataModel.getValue(CommunityContentModel::class.java)
                    items.add(item!!)

                    Log.d("dataModel", dataModel.key.toString())    // 각 데이터의 key값 (uuid)
                    itemKeyList.add(dataModel.key.toString())
                }

                Log.d("items", items.toString())
                rvAdapter.notifyDataSetChanged()    // RecyclerViewAdapter를 items 가 데이터를 받고 새로 리프래쉬 하게 하는 것

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("CommunityContentList","loadPost:onCancelled",error.toException())
            }
        }
        myRef.addValueEventListener(postListener)
        //------------------------------------------------------

        val rv :RecyclerView = findViewById(R.id.recyclerView)

        rv.adapter = rvAdapter

        // 아이템들 어떤 형식으로 보여줄지
        rv.layoutManager = GridLayoutManager(this,2)


        getBookmarkData()

        // firebase database에 데이터 집어넣기
//        myRef.push().setValue(
//            CommunityContentModel("imageTitle1", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FblYPPY%2Fbtq66v0S4wu%2FRmuhpkXUO4FOcrlOmVG4G1%2Fimg.png","https://philosopher-chan.tistory.com/1235?category=941578"))
////
//        myRef.push().setValue(
//            CommunityContentModel("imageTitle2", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FznKK4%2Fbtq665AUWem%2FRUawPn5Wwb4cQ8BetEwN40%2Fimg.png","https://philosopher-chan.tistory.com/1236?category=941578"))
////
//        myRef.push().setValue(
//            CommunityContentModel("imageTitle3","https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbtig9C%2Fbtq65UGxyWI%2FPRBIGUKJ4rjMkI7KTGrxtK%2Fimg.png","https://philosopher-chan.tistory.com/1237?category=941578"))
////
//        myRef.push().setValue(
//            CommunityContentModel("imageTitle4","https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcOYyBM%2Fbtq67Or43WW%2F17lZ3tKajnNwGPSCLtfnE1%2Fimg.png","https://philosopher-chan.tistory.com/1238?category=941578"))
////

//
//        items.add(CommunityContentModel("imageTitle1", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FblYPPY%2Fbtq66v0S4wu%2FRmuhpkXUO4FOcrlOmVG4G1%2Fimg.png","https://philosopher-chan.tistory.com/1235?category=941578"))
//        items.add(CommunityContentModel("imageTitle2", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FznKK4%2Fbtq665AUWem%2FRUawPn5Wwb4cQ8BetEwN40%2Fimg.png","https://philosopher-chan.tistory.com/1236?category=941578"))
//        items.add(CommunityContentModel("imageTitle3","https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbtig9C%2Fbtq65UGxyWI%2FPRBIGUKJ4rjMkI7KTGrxtK%2Fimg.png","https://philosopher-chan.tistory.com/1237?category=941578"))
//        items.add(CommunityContentModel("imageTitle4","https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcOYyBM%2Fbtq67Or43WW%2F17lZ3tKajnNwGPSCLtfnE1%2Fimg.png","https://philosopher-chan.tistory.com/1238?category=941578"))
//        items.add(CommunityContentModel("imageTitle5","https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fekn5wI%2Fbtq66UlN4bC%2F8NEzlyot7HT4PcjbdYAINk%2Fimg.png","https://philosopher-chan.tistory.com/1239?category=941578"))
//        items.add(CommunityContentModel("imageTitle6","https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F123LP%2Fbtq65qy4hAd%2F6dgpC13wgrdsnHigepoVT1%2Fimg.png","https://philosopher-chan.tistory.com/1240?category=941578"))
//        items.add(CommunityContentModel("imageTitle7","https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fl2KC3%2Fbtq64lkUJIN%2FeSwUPyQOddzcj6OAkPKZuk%2Fimg.png","https://philosopher-chan.tistory.com/1241?category=941578"))
//        items.add(CommunityContentModel("imageTitle8","https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmBh5u%2Fbtq651yYxop%2FX3idRXeJ0VQEoT1d6Hln30%2Fimg.png","https://philosopher-chan.tistory.com/1242?category=941578"))
//        items.add(CommunityContentModel("imageTitle9","https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FlOnja%2Fbtq69Tmp7X4%2FoUvdIEteFbq4Z0ZtgCd4p0%2Fimg.png","https://philosopher-chan.tistory.com/1243?category=941578"))
//        items.add(CommunityContentModel("imageTitle10","https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FNNrYR%2Fbtq64wsW5VN%2FqIaAsfmFtcvh4Bketug9m0%2Fimg.png","https://philosopher-chan.tistory.com/1244?category=941578"))
//        items.add(CommunityContentModel("imageTitle11","https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFtY3t%2Fbtq65q6P4Zr%2FWe64GM8KzHAlGE3xQ2nDjk%2Fimg.png","https://philosopher-chan.tistory.com/1248?category=941578"))
//        items.add(CommunityContentModel("imageTitle12","https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FOtaMq%2Fbtq67OMpk4W%2FH1cd0mda3n2wNWgVL9Dqy0%2Fimg.png","https://philosopher-chan.tistory.com/1249?category=941578"))

    }


    // 북마크 클릭한 값 db에서 찾기
    private fun getBookmarkData(){

        val postListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                bookmarkIdList.clear() // 기존 리스트를 클리어한 후 새로 쌓기
                for(dataModel in snapshot.children){
                    Log.d("dataModel", dataModel.key.toString())
                    bookmarkIdList.add(dataModel.key.toString())

                }
                Log.d("bookmarkIdList", bookmarkIdList.toString())
                rvAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("CommunityContentList","loadPost:onCancelled2",error.toException())
            }
        }
        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)


    }



}

