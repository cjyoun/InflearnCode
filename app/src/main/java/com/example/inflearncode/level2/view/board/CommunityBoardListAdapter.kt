package com.example.inflearncode.level2.view.board

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.example.inflearncode.R
import com.example.inflearncode.level2.util.FBAuth

class CommunityBoardListAdapter(val boardList:MutableList<CommunityBoardModel>): BaseAdapter() {
    override fun getCount(): Int {
        return boardList.size
    }

    override fun getItem(position: Int): Any {
        return boardList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = convertView
        // ListView의 View 재활용 특징에 대해서 검색해 볼 것
//        if (view == null){
            view = LayoutInflater.from(parent?.context).inflate(R.layout.community_board_list_item, parent, false)
//        }

        val title = view?.findViewById<TextView>(R.id.boardTitle)
        title!!.text = boardList[position].title

        val content = view?.findViewById<TextView>(R.id.boardContent)
        content!!.text = boardList[position].content

        val time = view?.findViewById<TextView>(R.id.boardTime)
        time!!.text = boardList[position].time

        // 아이템 뷰
        val itemLinearLayoutView = view?.findViewById<LinearLayout>(R.id.boardItemView)
        // 게시글의 키값이 핸드폰 uid 값과 같다면 게시글 배경 변경
        if(boardList[position].uid.equals(FBAuth.getUid())){
            itemLinearLayoutView?.setBackgroundColor(Color.parseColor("#ffa500"))
        }

        return view!!


    }

}