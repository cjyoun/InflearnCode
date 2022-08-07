package com.example.inflearncode.level2.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.inflearncode.R
import com.example.inflearncode.databinding.FragmentCommunityBookmarkBinding


class CommunityBookmarkFragment : Fragment() {

    private lateinit var binding: FragmentCommunityBookmarkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community_bookmark, container, false)

        // 홈 fragment로 이동
        binding.homeTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityBookmarkFragment_to_communityHomeFragment)
        }

        // 꿀팁 fragment로 이동
        binding.tipTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityBookmarkFragment_to_communityTipFragment)
        }

        // 스토어 fragment로 이동
        binding.storeTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityBookmarkFragment_to_communityStoreFragment)
        }

        // talk fragment로 이동
        binding.talkTap.setOnClickListener {
            // 네비게이션으로 이동
            it.findNavController().navigate(R.id.action_communityBookmarkFragment_to_communityTalkFragment)
        }


        return binding.root
    }

}