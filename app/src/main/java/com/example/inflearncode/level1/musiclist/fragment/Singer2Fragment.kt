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

class Singer2Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_singer2, container, false)

        // fragment_singer1.xml에 선언한 RecyclerView id 값 (xml에 선언한 RecyclerView id)
        val rv = view.findViewById<RecyclerView>(R.id.singer2RV)

        val items = mutableListOf<String>()
        items.add("좋은날")
        items.add("잔소리")
        items.add("스물셋")

        val rvAdapter = MusicRVAdapter(items)
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(context)

        // 1번째 이미지 눌렀을 때
        view.findViewById<ImageView>(R.id.singer1).setOnClickListener {
            it.findNavController().navigate(R.id.action_singer2Fragment_to_singer1Fragment)
        }

        // 3번째 이미지 눌렀을 때
        view.findViewById<ImageView>(R.id.singer3).setOnClickListener {
            it.findNavController().navigate(R.id.action_singer2Fragment_to_singer3Fragment)
        }

        return view
    }

}