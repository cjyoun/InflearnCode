package com.example.inflearncode.level1.mango

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inflearncode.R
import com.example.inflearncode.level1.mango.model.ContentsModel

class MangoActivity : AppCompatActivity() {

    private val items = mutableListOf<ContentsModel>()  // RecyclerAdapter에 사용될 아이템들

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mango)

        items.add(
            ContentsModel(
                "https://www.mangoplate.com/restaurants/4qnCi8_87ger"
                , "https://mp-seoul-image-production-s3.mangoplate.com/47875_1596002855152039.jpg?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80"
                , "당도"
            )
        )

        items.add(
            ContentsModel(
                "https://www.mangoplate.com/restaurants/NCw7VtUKEx9l"
                , "https://mp-seoul-image-production-s3.mangoplate.com/398310/3ssjt6flqzb2br.jpg?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80"
                , "라운지앤바"
            )
        )

        items.add(
            ContentsModel(
                "https://www.mangoplate.com/restaurants/B-VEMWjnWhMt"
                , "https://mp-seoul-image-production-s3.mangoplate.com/2252175_1655571050867486.jpg?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80"
                , "스페이스서종"
            )
        )

        items.add(
            ContentsModel(
                "https://www.mangoplate.com/restaurants/9EVyzauilukl"
                , "https://mp-seoul-image-production-s3.mangoplate.com/722502_1653195022186457.jpg?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80"
                , "시라카와"
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.mangoRV) // xml에서 RecylerView id 값 가져오기
        val rvAdapter = MangoRVAdapter(baseContext, items)   // 생성한 RecyclerViewAdapter 에 items 넣기
        recyclerView.adapter = rvAdapter    // xml 의 RecylerView의 adapter에 생성한 adapter 넣기
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 한 열에 두 행이 보이도록 하기 , RecyclerView가 어떤 Layout으로 보일지




    }
}