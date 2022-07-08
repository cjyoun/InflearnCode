package com.example.inflearncode.level1.wisesaying

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.inflearncode.R
import com.example.inflearncode.databinding.ActivitySentenceBinding

class SentenceActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySentenceBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sentence)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_sentence)

        val sentenceList = mutableListOf<String>()
        sentenceList.add("노는 것도 일하는 것 만큼 중요하다.")
        sentenceList.add("결점이 없는 친구를 사귀려고 한다면 평생 친구를 가질 수 없을 것이다.")
        sentenceList.add("승자는 눈을 밟아 길을 만들지만 패자는 눈이 녹기만을 기다린다.")
        sentenceList.add("뛰어난 말에게도 체력이 필요하다.")
        sentenceList.add("그 사람 입장에 서기 전까지 절대 그 사람을 욕하거나 탓하지 마라.")

        // List를 adapter에 넘겨주기
        val sentenceAdapter = SentenceListViewAdapter(sentenceList)
        val listView = binding.sentenceListView

        // id값 sentenceListView 라고 되어있는 ListView의 adapter에 아까 만든 Adapter 넣기
        listView.adapter = sentenceAdapter



    }

}