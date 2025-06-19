package com.example.myapplication.api

import com.example.myapplication.data.UnsplashItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

private const val CLIENT_ID = "Client-ID"
private const val ACCESS_KEY = "IXlHIcVOWsJcvxHJnojZLNkGnA1aqeu0G2uelsw51r4"

interface UnsplashApi {

    @Headers("Authorization: $CLIENT_ID $ACCESS_KEY")
    @GET("photos")
    fun fetchPhotos(): Call<List<UnsplashItem>>
}