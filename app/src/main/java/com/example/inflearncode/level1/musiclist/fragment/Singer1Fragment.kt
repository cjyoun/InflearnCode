package com.example.inflearncode.level1.musiclist.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.inflearncode.R
import com.example.inflearncode.level1.musiclist.MusicRVAdapter

class Singer1Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_singer1, container, false)

        // fragment_singer1.xml에 선언한 RecyclerView id 값 (xml에 선언한 RecyclerView id)
        val rv = view.findViewById<RecyclerView>(R.id.singer1RV)

        val items = mutableListOf<String>()
        items.add("야생화")
        items.add("눈의꽃")
        items.add("Gift")

        val rvAdapter = MusicRVAdapter(items)
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(context)


        // 1번째 사진이기 때문에 첫번째 사진 눌렀을 때는 화면 이동이 없으므로 안만듦
        // 2번째 이미지 눌렀을 때
        val image2 = view.findViewById<ImageView>(R.id.singer2)
        image2.setOnClickListener {
            it.findNavController().navigate(R.id.action_singer1Fragment_to_singer2Fragment)
        }

        // 3번째 이미지 눌렀을 때
        val image3 = view.findViewById<ImageView>(R.id.singer3)
        image3.setOnClickListener {
            it.findNavController().navigate(R.id.action_singer1Fragment_to_singer3Fragment)
        }


        return view

    }

}