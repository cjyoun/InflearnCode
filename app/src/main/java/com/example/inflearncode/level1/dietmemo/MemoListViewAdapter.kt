package com.example.inflearncode.level1.dietmemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.inflearncode.R
import com.example.inflearncode.level1.dietmemo.model.DataModel

class MemoListViewAdapter(val list: MutableList<DataModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var convertView = convertView
        if(convertView ==null){
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.memo_listvies_item, parent, false)
        }

        val dietDate = convertView!!.findViewById<TextView>(R.id.memoListViewDateArea)
        val dietMemo = convertView!!.findViewById<TextView>(R.id.memoListViewMemoArea)

        dietDate!!.text = list[position].date
        dietMemo!!.text = list[position].memo

        return convertView
    }

}