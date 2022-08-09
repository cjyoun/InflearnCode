package com.example.inflearncode.level2.view.contentlist

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.inflearncode.R
import com.example.inflearncode.level2.util.FirebaseAuth
import com.example.inflearncode.level2.util.FirebaseRef

class CommunityContentRVAdapter (
        val context: Context,       // context
        val items : ArrayList<CommunityContentModel>,   // RecyclerView를 통해 보여줄 itemList
        val keyList: ArrayList<String>,         //  Bookmark에 해당하는 keyList (화면에 보이는 item들에 해당하는 bookmark key값)
        val bookmarkIdList: MutableList<String> // 이전에 bookmark를 체크한 것들의 id리스트 (firebase db에 있는 리스트들)
    ) : RecyclerView.Adapter<CommunityContentRVAdapter.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommunityContentRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.community_content_rv_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CommunityContentRVAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position], keyList[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindItems(item: CommunityContentModel, key: String){

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


            val bookMarkArea = itemView.findViewById<ImageView>(R.id.bookmarkArea)

            // bookmarkIdList에 있는 값 중에 선택한 북마크의 key 값이 있다면 검정색으로 없다면 하얀색으로
            if(bookmarkIdList.contains(key)){
                bookMarkArea.setImageResource(R.drawable.bookmark_color)
            } else {
                bookMarkArea.setImageResource(R.drawable.bookmark_white)
            }

            // 북마크 클릭
            bookMarkArea.setOnClickListener {
                Log.d("북마크 클릭 ", FirebaseAuth.getUid())
                Toast.makeText(context, "${item.title} 북마크 클릭 - $key", Toast.LENGTH_LONG).show()

                // 북마크가 있을 때
                if(bookmarkIdList.contains(key)){
                    // bookmarkIdList에서도 클릭한 key 값을 빼서 색깔이 변경되도록 함.
                    bookmarkIdList.remove(key)

                    // Firebase DB에 해당 기기의 uid 아래에 클릭한 북마크에 해당하는 key 값을 삭제
                    FirebaseRef.bookmarkRef
                        .child(FirebaseAuth.getUid())
                        .child(key)
                        .removeValue()
                } else{
                    // 북마크가 없을 때
                    // Firebase DB에 해당 기기의 uid 아래에 클릭한 북마크에 해당하는 key 값을 저장
                    FirebaseRef.bookmarkRef
                        .child(FirebaseAuth.getUid())
                        .child(key)
                        .setValue(CommunityBookmarkModel(true))
                }


            }




        }

    }

}