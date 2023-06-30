package com.example.navigationdanapi.Following

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigationdanapi.DetailViewModel
import com.example.navigationdanapi.remote.ApiConfig
import com.example.navigationdanapi.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel: ViewModel() {
    private val _detailFollowing = MutableLiveData<List<UserResponse>>(listOf())
    val detailFollowing: LiveData<List<UserResponse>> = _detailFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun findFollowing(following: String) {
        _isLoading.value = true
        val client= ApiConfig.getApiService().getFollowing(following)
        client.enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(
                call: Call<List<UserResponse>>,
                response: Response<List<UserResponse>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _detailFollowing.value = response.body()
                } else {
                    Log.e(DetailViewModel.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                _isLoading.value = false
                Log.e(DetailViewModel.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }}