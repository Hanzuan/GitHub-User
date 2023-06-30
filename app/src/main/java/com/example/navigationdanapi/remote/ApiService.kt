package com.example.navigationdanapi.remote

import com.example.navigationdanapi.response.DetailUserResponse
import com.example.navigationdanapi.response.SearchUserResponse
import com.example.navigationdanapi.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: ")
    fun getUser(
        @Query("q") username: String
    ): Call<SearchUserResponse>

    @GET("user/{username}")
    @Headers("Authorization: ")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("user/{username}/following")
    @Headers("Authorization: ")
    fun getFollowing(
        @Path("username") username: String
    ):Call<List<UserResponse>>

    @GET("user/{username}/followers")
    @Headers("Authorization: ")
    fun getFollower(
        @Path("username") username: String
    ):Call<List<UserResponse>>
}