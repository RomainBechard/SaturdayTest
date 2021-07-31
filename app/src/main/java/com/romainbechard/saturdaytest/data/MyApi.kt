package com.romainbechard.saturdaytest.data

import com.romainbechard.saturdaytest.BuildConfig
import com.romainbechard.saturdaytest.data.model.SearchPhotoResponse
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("/api")
    suspend fun searchPhotos(
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("q", encoded = true) keywords: String,
        @Query("image_type") imageType: String = "photo"
    ): SearchPhotoResponse
}