package com.example.exampleest.data.api

import com.example.exampleest.data.model.City
import retrofit2.Response
import retrofit2.http.GET

interface WeatherService {
    @GET("location/search/?query=a")
    suspend fun getCities() : Response<List<City>>
}