package com.example.inflearncode.level2.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inflearncode.R
import com.example.inflearncode.databinding.FragmentCommunityBookmarkBinding
import com.example.inflearncode.level2.util.FBAuth
import com.example.inflearncode.level2.util.FBRef
import com.example.inflearncode.level2.view.contentlist.CommunityBookmarkRVAdapter
import com.example.inflearncode.level2.view.contentlist.CommunityContentModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class CommunityBookmarkFragment : Fragment() {
    private val TAG = CommunityBookmarkFragment::class.java.simpleName

    lateinit var rvAdapter: CommunityBookmarkRVAdapter
    val bookmarkIdList = mutableListOf<String>()
    val items = ArrayList<CommunityContentModel>()
    val itemKeyList = ArrayList<String>()

    private lateinit var binding: FragmentCommunityBookmarkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community_bookmark, container, false)


        // 1. 사용자가가 북마크한 정보를 다 가져옴
        getBookmarkData()


        rvAdapter = CommunityBookmarkRVAdapter(requireContext(), items, itemKeyList, bookmarkIdList)
        val rv : RecyclerView = binding.communityBookmarkRV
        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(requireContext(), 2)



        // 홈 fragment로 이동
        binding.homeTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityBookmarkFragment_to_communityHomeFragment)
        }

        // 꿀팁 fragment로 이동
        binding.tipTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityBookmarkFragment_to_communityTipFragment)
        }

        // 스토어 fragment로 이동
        binding.storeTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityBookmarkFragment_to_communityStoreFragment)
        }

        // talk fragment로 이동
        binding.talkTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityBookmarkFragment_to_communityTalkFragment)
        }


        return binding.root
    }

    // 카테고리 데이터들 가져오기
    private fun getCategoryData(){
        // firebaes database 값들 가져오기
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for(dataModel in snapshot.children){
                    Log.d(TAG, dataModel.toString())    // item 데이터 리스트
                    val item = dataModel.getValue(CommunityContentModel::class.java)

                    // 3. 전체 컨텐츠 중에서 사용자가 북마크한 컨텐츠만 보여줌
                    // 카테고리에 있는 데이터를 가져온 것들 중 bookmarkIdList에 있다면 그것들만 추가해주기
                    if(bookmarkIdList.contains(dataModel.key.toString())){
                        items.add(item!!)
                        itemKeyList.add(dataModel.key.toString())
                    }

                }

                rvAdapter.notifyDataSetChanged()    // RecyclerViewAdapter를 items 가 데이터를 받고 새로 리프래쉬 하게 하는 것

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG,"loadPost:onCancelled",error.toException())
            }
        }
        FBRef.category1.addValueEventListener(postListener)   // category1 카테고리
        FBRef.category2.addValueEventListener(postListener)   // category1 카테고리
    }


    // 북마크크데이터들 가져오기
    private fun getBookmarkData(){
        // firebaes database 값들 가져오기
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for(dataModel in snapshot.children){
                    Log.d("dataModel", dataModel.toString())
                    bookmarkIdList.add(dataModel.key.toString())
                }

                // 2. 전체 카테고리에 있는 컨텐츠 데이터들 전부 가져옴
                getCategoryData()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG,"loadPost:onCancelled",error.toException())
            }
        }
        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)    // 북마크 리스트 가져오기
    }


}