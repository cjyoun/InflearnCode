package com.example.inflearncode.level2.view.comment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.example.inflearncode.R
import com.example.inflearncode.level2.util.FBAuth

class CommunityBoardCommentLVAdapter(val commentList: MutableList<CommunityBoardCommentModel>): BaseAdapter() {
    override fun getCount(): Int {
        return commentList.size
    }

    override fun getItem(position: Int): Any {
        return commentList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        // ListView의 View 재활용 특징에 대해서 검색해 볼 것
        if (view == null){
            view = LayoutInflater.from(parent?.context).inflate(R.layout.community_comment_list_item, parent, false)
        }

        val title = view?.findViewById<TextView>(R.id.commentTitle)
        title!!.text = commentList[position].commentTitle

        val time = view?.findViewById<TextView>(R.id.commentTime)
        time!!.text = commentList[position].commentCreatedTime


        return view!!
    }

}