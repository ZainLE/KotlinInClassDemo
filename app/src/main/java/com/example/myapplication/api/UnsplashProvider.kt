package com.example.myapplication.api

import com.example.myapplication.data.UnsplashItem
import com.example.myapplication.data.cb.UnsplashResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val BASE_URL = "https://api.unsplash.com/"


class UnsplashProvider {

    private val unsplashApiService: UnsplashApi by lazy {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(UnsplashApi::class.java)
    }

    fun fetchPhotos(cb : UnsplashResult) {
        unsplashApiService.fetchPhotos().enqueue(object : Callback<List<UnsplashItem>> {
            override fun onResponse(
                call: Call<List<UnsplashItem>>,
                response: Response<List<UnsplashItem>>
            ) {
                val output = response.body()
                if (response.isSuccessful && output != null) {
                    cb.onDataFetchedSuccess(output)
                } else {
                    cb.onDataFetchedFailed("${response.code()}: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<UnsplashItem>>, t: Throwable) {
                cb.onDataFetchedFailed("Error: ${t.message}")
            }
        })
    }

    fun fetchPhotoById(photoId: String, cb: (UnsplashItem?) -> Unit, onError: (String) -> Unit) {
        unsplashApiService.fetchPhotoById(photoId).enqueue(object : Callback<UnsplashItem> {
            override fun onResponse(call: Call<UnsplashItem>, response: Response<UnsplashItem>) {
                if (response.isSuccessful) {
                    cb(response.body())
                } else {
                    onError("${response.code()}: ${response.errorBody()?.string() ?: "Unknown error"}")
                }
            }
            override fun onFailure(call: Call<UnsplashItem>, t: Throwable) {
                onError(t.message ?: "Unknown error")
            }
        })
    }
}