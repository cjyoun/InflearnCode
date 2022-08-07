package com.example.inflearncode.level2.view.fragment

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