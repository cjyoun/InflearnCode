package com.example.inflearncode.level1.musiclist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inflearncode.R

class MusicRVAdapter(val items:MutableList<String>) : RecyclerView.Adapter<MusicRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicRVAdapter.ViewHolder {
        // item xml을 adapter와 연결시키기
        val view = LayoutInflater.from(parent.context).inflate(R.layout.music_rv_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicRVAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size   // 리스트 개수
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        // items 리스트에 있는 텍스트 값을 xml 요소에 대입
        fun bindItems(item : String){
            val rv_text = itemView.findViewById<TextView>(R.id.rvTextId)
            rv_text.text = item
        }
    }

}