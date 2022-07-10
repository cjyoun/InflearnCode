package com.example.inflearncode.level1.musiclist.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inflearncode.R
import com.example.inflearncode.level1.musiclist.MusicRVAdapter

class Singer3Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_singer3, container, false)

        // fragment_singer1.xml에 선언한 RecyclerView id 값 (xml에 선언한 RecyclerView id)
        val rv = view.findViewById<RecyclerView>(R.id.singer3RV)

        val items = mutableListOf<String>()
        items.add("만약에")
        items.add("Weekend")
        items.add("11:11")

        val rvAdapter = MusicRVAdapter(items)
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(context)

        // 1번째 이미지 눌렀을 때
        view.findViewById<ImageView>(R.id.singer1).setOnClickListener {
            it.findNavController().navigate(R.id.action_singer3Fragment_to_singer1Fragment)
        }

        // 2번째 이미지 눌렀을 때
        view.findViewById<ImageView>(R.id.singer2).setOnClickListener {
            it.findNavController().navigate(R.id.action_singer3Fragment_to_singer2Fragment)
        }
        return view
    }

}