package com.example.pagingapplication.api


import com.example.pagingapplication.BuildConfig
import com.example.pagingapplication.data.model.MainResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("api/")
    suspend fun getPhotoApiData(

        @Query("page")
        page: Int,
        @Query("q")
        query: String,
        @Query("key")
        key: String = CLIENT_ID
    ): MainResponse


    companion object {

        const val CLIENT_ID = BuildConfig.PIXABAY_ACCESS_KEY
        const val BASE_URL = "https://pixabay.com/"

    }
}