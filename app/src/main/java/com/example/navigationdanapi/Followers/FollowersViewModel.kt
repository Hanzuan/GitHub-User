package com.example.navigationdanapi.Followers

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

class FollowerViewModel: ViewModel() {

    private val _detailFollower = MutableLiveData<List<UserResponse>>(listOf())
    val detailFollower: LiveData<List<UserResponse>> = _detailFollower

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun findFollower(follower: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollower(follower)
        client.enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(
                call: Call<List<UserResponse>>,
                response: Response<List<UserResponse>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _detailFollower.value = response.body()
                } else {
                    Log.e(DetailViewModel.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                _isLoading.value = false
                Log.e(DetailViewModel.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

}