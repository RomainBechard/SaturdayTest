package com.romainbechard.saturdaytest

import android.app.Application
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.romainbechard.saturdaytest.data.MyApi
import com.romainbechard.saturdaytest.data.MyRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class SaturdayTestApplication : Application() {

    lateinit var repository: MyRepository

    private val myApi: MyApi = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(
            JacksonConverterFactory.create(
                ObjectMapper()
                    .registerKotlinModule()
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            )
        )
        .client(
            OkHttpClient().newBuilder()
                .addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .build()
        )
        .build()
        .create(MyApi::class.java)

    override fun onCreate() {
        super.onCreate()

        repository = MyRepository(myApi)
    }
}