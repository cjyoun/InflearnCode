package com.example.inflearncode.level2.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.inflearncode.R
import com.example.inflearncode.databinding.FragmentCommunityStoreBinding
import com.example.inflearncode.databinding.FragmentCommunityTipBinding
import com.example.inflearncode.level2.view.contentlist.CommunityContentListActivity
import com.example.inflearncode.level2.view.contentlist.CommunityContentRVAdapter


class CommunityTipFragment : Fragment() {

    private lateinit var binding: FragmentCommunityTipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community_tip, container,false)

        // 카테고리1 클릭
        binding.category1.setOnClickListener {
            val intent = Intent(context,CommunityContentListActivity::class.java)
            intent.putExtra("category","category1")
            startActivity(intent)
        }
        // 카테고리2 클릭
        binding.category2.setOnClickListener {
            val intent = Intent(context,CommunityContentListActivity::class.java)
            intent.putExtra("category","category2")
            startActivity(intent)
        }


        // 홈 fragment로 이동
        binding.homeTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityTipFragment_to_communityHomeFragment)
        }

        // talk fragment로 이동
        binding.talkTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityTipFragment_to_communityTalkFragment)
        }

        // 스토어 fragment로 이동
        binding.storeTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityTipFragment_to_communityStoreFragment)
        }

        // 북마크 fragment로 이동
        binding.bookMarkTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityTipFragment_to_communityBookmarkFragment)
        }

        return binding.root
    }

}