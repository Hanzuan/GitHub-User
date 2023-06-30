package com.example.navigationdanapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.navigationdanapi.Adapter.SectionsPagerAdapter
import com.example.navigationdanapi.databinding.ActivityDetailBinding
import com.example.navigationdanapi.response.DetailUserResponse
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val DetailViewModel by viewModels<DetailViewModel>()

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.Follower,
            R.string.Following
        )
        const val username: String = "USERNAME"
        const val avatar: String = "AVATAR"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DetailViewModel.detailUser.observe(this) { detailUser ->
            setUserDetailData(detailUser)
        }
        DetailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        val uname = intent.getStringExtra(username)
        DetailViewModel.findUserDetail(uname.toString())

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tab)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    private fun setUserDetailData(detailUser: DetailUserResponse?) {
        Glide.with(this)
            .load(detailUser?.avatarUrl)
            .into(binding.imgProfile)

        binding.tvUsername.text = detailUser?.login
        binding.tvName.text = detailUser?.name ?: "No Name"

        binding.tvFollowingCount.text = detailUser?.followers.toString()
        binding.tvFollowingCount.text = detailUser?.following.toString()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}