package com.example.navigationdanapi.Following

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationdanapi.Adapter.UserAdapter
import com.example.navigationdanapi.DetailActivity
import com.example.navigationdanapi.databinding.FragmentFollowingBinding
import com.example.navigationdanapi.response.UserResponse

class FollowingFragment : Fragment() {

    private lateinit var followingBinding: FragmentFollowingBinding
    private lateinit var followingViewModel: FollowingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        followingBinding = FragmentFollowingBinding.inflate(inflater, container, false)
        followingViewModel = ViewModelProvider(this).get(FollowingViewModel::class.java)
        return followingBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followingViewModel.findFollowing(requireActivity().intent.getStringExtra(DetailActivity.username).toString())

        followingViewModel.detailFollowing.observe(viewLifecycleOwner) {detailFollowing ->
            setFollowing(detailFollowing)
        }

        followingViewModel.isLoading.observe(viewLifecycleOwner) {isLoading ->
            showLoading(isLoading)
        }
    }

    private fun setFollowing(detailFollowing: List<UserResponse>) {
        val adapter = UserAdapter(detailFollowing)
        followingBinding.rvFollowing.adapter = adapter
        followingBinding.rvFollowing.layoutManager = LinearLayoutManager(context)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            followingBinding.progressBar.visibility = View.VISIBLE
        } else {
            followingBinding.progressBar.visibility = View.GONE
        }
    }
}