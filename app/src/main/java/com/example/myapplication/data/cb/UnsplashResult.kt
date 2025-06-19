package com.example.myapplication.data.cb

import com.example.myapplication.data.UnsplashItem

interface UnsplashResult {

    fun onDataFetchedSuccess(images : List<UnsplashItem>)

    fun onDataFetchedFailed(message: String)
}