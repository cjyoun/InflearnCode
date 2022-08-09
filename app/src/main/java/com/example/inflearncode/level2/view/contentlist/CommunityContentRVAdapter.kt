package com.example.inflearncode.level2.view.contentlist

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.inflearncode.R

class CommunityContentRVAdapter (val context: Context, val items : ArrayList<CommunityContentModel>): RecyclerView.Adapter<CommunityContentRVAdapter.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommunityContentRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.community_content_rv_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CommunityContentRVAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindItems(item: CommunityContentModel){

            // 아이템들 클릭 이벤트
            itemView.setOnClickListener {
                Toast.makeText(context, item.title, Toast.LENGTH_LONG).show()

                // webView 띄워주는 곳으로 Intent 이동
                val intent = Intent(context, CommunityContentShowActivity::class.java)
                intent.putExtra("url", item.webUrl)
                itemView.context.startActivity(intent)
            }

            val contentTitle = itemView.findViewById<TextView>(R.id.textArea)
            contentTitle.text = item.title

            val imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)
            Glide.with(context).load(item.imageUrl).into(imageViewArea)

            // 북마크 클릭
            val bookMarkArea = itemView.findViewById<ImageView>(R.id.bookmarkArea)
            bookMarkArea.setOnClickListener {
                Toast.makeText(context, "${item.title} 북마크 클릭", Toast.LENGTH_LONG).show()
            }




        }

    }

}