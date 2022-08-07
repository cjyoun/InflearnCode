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
import com.example.inflearncode.databinding.FragmentCommunityTalkBinding


class CommunityTalkFragment : Fragment() {

    private lateinit var binding: FragmentCommunityTalkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community_talk, container,false)

        // 홈 fragment로 이동
        binding.homeTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityTalkFragment_to_communityHomeFragment)
        }

        // 꿀팁 fragment로 이동
        binding.tipTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityTalkFragment_to_communityTipFragment)
        }

        // 스토어 fragment로 이동
        binding.storeTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityTalkFragment_to_communityStoreFragment)
        }

        // 북마크 fragment로 이동
        binding.bookMarkTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityTalkFragment_to_communityBookmarkFragment)
        }

        return binding.root
    }

}