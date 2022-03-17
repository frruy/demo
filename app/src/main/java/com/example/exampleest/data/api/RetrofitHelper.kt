package com.example.exampleest.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private var INSTANCE: Retrofit? = null
    private const val baseUrl = "https://www.metaweather.com/api/"

    fun getInstance(): Retrofit {
        if (INSTANCE == null) {
            synchronized(Retrofit::class) {
                INSTANCE = Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    // we need to add converter factory to
                    // convert JSON object to Java object
                    .build()
            }
        }
        return INSTANCE!!
    }
}