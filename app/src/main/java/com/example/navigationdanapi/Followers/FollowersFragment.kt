package com.example.navigationdanapi.Followers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationdanapi.Adapter.UserAdapter
import com.example.navigationdanapi.DetailActivity
import com.example.navigationdanapi.databinding.FragmentFollowersBinding
import com.example.navigationdanapi.response.UserResponse

class FollowersFragment : Fragment() {
    private lateinit var followersBinding: FragmentFollowersBinding
    private lateinit var followersViewModel: FollowerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        followersBinding = FragmentFollowersBinding.inflate(inflater, container, false)
        followersViewModel = ViewModelProvider(this).get(FollowerViewModel::class.java)
        return followersBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followersViewModel.findFollower(requireActivity().intent.getStringExtra(DetailActivity.username).toString())
        followersViewModel.detailFollower.observe(viewLifecycleOwner) { detailFollower ->
            setFollower(detailFollower)
        }

        followersViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            showLoading(isLoading)
        }
    }

    private fun setFollower(detailFollower: List<UserResponse>) {
        val adapter = UserAdapter(detailFollower)
        followersBinding.rvFollower.adapter = adapter
        followersBinding.rvFollower.layoutManager = LinearLayoutManager(context)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            followersBinding.progressBar.visibility = View.VISIBLE
        } else {
            followersBinding.progressBar.visibility = View.GONE
        }
    }
}