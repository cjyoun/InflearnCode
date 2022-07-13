package com.example.inflearncode.level1.mango

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.inflearncode.R
import com.example.inflearncode.level1.mango.model.ContentsModel

class MangoRVAdapter(val context: Context, val list : MutableList<ContentsModel>) : RecyclerView.Adapter<MangoRVAdapter.ViewHoler>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangoRVAdapter.ViewHoler {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.mango_rv_item, parent, false)
        return ViewHoler(v)
    }

    // RecyclerView의 item 들에 클릭 이벤트 주기
    interface ItemClick{
        fun onClick(view: View, position: Int)
    }
    var itemClick : ItemClick? = null

    override fun onBindViewHolder(holder: MangoRVAdapter.ViewHoler, position: Int) {

        // RecyclerView의 item 들에 클릭 이벤트 주기
        if(itemClick != null){
            holder?.itemView.setOnClickListener{ v->
                itemClick!!.onClick(v, position)
            }
        }

        holder.bindiTems(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class ViewHoler(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindiTems(item: ContentsModel) {

            val rv_img = itemView.findViewById<ImageView>(R.id.mangoRVImageArea)
            val rv_text = itemView.findViewById<TextView>(R.id.mangoRVTextArea)

            rv_text.text = item.titleText

            // glide로 web에서 이미지 가져오기 , web에 있는 url 을 가져오는 것이기 때문에 인터넨 퍼미션 추가해줘야 함.
            Glide.with(context)
                .load(item.imageUrl)
                .into(rv_img)




        }

    }
}