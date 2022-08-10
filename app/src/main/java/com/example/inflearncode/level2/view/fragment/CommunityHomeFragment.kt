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
import com.example.inflearncode.databinding.FragmentCommunityHomeBinding
import com.example.inflearncode.level2.util.FBRef
import com.example.inflearncode.level2.view.contentlist.CommunityBookmarkRVAdapter
import com.example.inflearncode.level2.view.contentlist.CommunityContentModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class CommunityHomeFragment : Fragment() {

    private lateinit var binding : FragmentCommunityHomeBinding

    private val TAG = CommunityHomeFragment::class.java.simpleName
    val bookmarkIdList = mutableListOf<String>()
    val items = ArrayList<CommunityContentModel>()
    val itemKeyList = ArrayList<String>()
    lateinit var rvAdapter: CommunityBookmarkRVAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community_home, container, false)

        // 북마크 fragment로 이동
        binding.bookMarkTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityHomeFragment_to_communityBookmarkFragment)

        }

        // 꿀팁 fragment로 이동
        binding.tipTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityHomeFragment_to_communityTipFragment)

        }

        // 스토어 fragment로 이동
        binding.storeTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityHomeFragment_to_communityStoreFragment)

        }

        // talk fragment로 이동
        binding.talkTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityHomeFragment_to_communityTalkFragment)

        }

        rvAdapter = CommunityBookmarkRVAdapter(requireContext(), items, itemKeyList, bookmarkIdList)
        val rv : RecyclerView = binding.mainRV
        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(requireContext(), 2)

        getCategoryData()
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
                    items.add(item!!)
                    itemKeyList.add(dataModel.key.toString())
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

}