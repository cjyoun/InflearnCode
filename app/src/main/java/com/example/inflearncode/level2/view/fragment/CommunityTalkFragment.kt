package com.example.inflearncode.level2.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.inflearncode.R
import com.example.inflearncode.databinding.FragmentCommunityTalkBinding
import com.example.inflearncode.level2.util.FBRef
import com.example.inflearncode.level2.view.board.CommunityBoardInsideActivity
import com.example.inflearncode.level2.view.board.CommunityBoardListAdapter
import com.example.inflearncode.level2.view.board.CommunityBoardModel
import com.example.inflearncode.level2.view.board.CommunityBoardWriteActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class CommunityTalkFragment : Fragment() {
    private val TAG = CommunityTalkFragment::class.java.simpleName
    private lateinit var binding: FragmentCommunityTalkBinding

    private val boardDataList = mutableListOf<CommunityBoardModel>()    // firebase에서 가져오는 게시판 리스트
    private lateinit var boardListAdpater : CommunityBoardListAdapter
    private val boardKeyList = mutableListOf<String>()  // 게시판 리스트 키 값

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community_talk, container,false)


        boardListAdpater = CommunityBoardListAdapter(boardDataList)
        binding.boardListView.adapter = boardListAdpater



        binding.boardListView.setOnItemClickListener { parent, view, position, id ->

            // 첫번째 방법 - Intent로 데이터들 다 넘겨서 이동된 Activity에서 보여주기
//            val intent = Intent(context, CommunityBoardInsideMainActivity::class.java)
//            intent.putExtra("title", boardDataList[position].title)
//            intent.putExtra("content", boardDataList[position].content)
//            intent.putExtra("time", boardDataList[position].time)
//            startActivity(intent)

            // 두번째 방법 - id값을 가지고 firebase에서 가져오기
            val intent = Intent(context, CommunityBoardInsideActivity::class.java)
            intent.putExtra("key",boardKeyList[position])
            startActivity(intent)

        }



        // 글쓰기 버튼 클릭
        binding.talkWriteBtn.setOnClickListener {
            val intent = Intent(context, CommunityBoardWriteActivity::class.java)
            startActivity(intent)
        }


        // 홈 fragment로 이동
        binding.homeTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityTalkFragment_to_communityHomeFragment)
        }

        // 꿀팁 fragment로 이동
        binding.tipTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityTalkFragment_to_communityTipFragment)
        }

        // 스토어 fragment로 이동
        binding.storeTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityTalkFragment_to_communityStoreFragment)
        }

        // 북마크 fragment로 이동
        binding.bookMarkTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityTalkFragment_to_communityBookmarkFragment)
        }

        // firebase에서 게시글 데이터 가져오기
       getFBBoardData()

        return binding.root
    }


    // firebase에서 게시글 데이터 가져오기
    private fun getFBBoardData(){

        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                boardDataList.clear()
                for(dataModel in snapshot.children){
                    Log.d(TAG, dataModel.toString())
                    val item = dataModel.getValue(CommunityBoardModel::class.java)
                    boardDataList.add(item!!)
                    boardKeyList.add(dataModel.key.toString())

                }

                Log.d(TAG, boardDataList.toString())
                boardDataList.reverse() // 데이터를 뒤집기 -> 최근 추가된 값이 처음으로 나오기   게시판 글 데이터
                boardKeyList.reverse() // 데이터를 뒤집기 -> 최근 추가된 값이 처음으로 나오기    게시판 글 키값
                boardListAdpater.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("CommunityContentList","loadPost:onCancelled2",error.toException())
            }
        }
        FBRef.boardRef.addValueEventListener(postListener)
    }

}