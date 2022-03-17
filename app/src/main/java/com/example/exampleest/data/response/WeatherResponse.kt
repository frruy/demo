package com.example.exampleest.data.response

import android.content.Context
import com.example.exampleest.data.api.RetrofitHelper
import com.example.exampleest.data.api.WeatherService
import com.example.exampleest.data.model.City
import com.example.exampleest.data.room.WeatherDao
import com.example.exampleest.data.room.WeatherDataBase
import com.example.exampleest.utils.isNetworkAvailable


class WeatherResponse(private val context: Context) {
    private val weatherService: WeatherService =
        RetrofitHelper.getInstance().create(WeatherService::class.java)
    private val weatherDao: WeatherDao = WeatherDataBase.getInstance(context).WeatherDao()

    suspend fun getCities(): List<City>? {

        // TODO: handle service error case

        var resultCities = weatherDao.getCities()
        if (resultCities.isNullOrEmpty() && isNetworkAvailable(context)) {
            resultCities = weatherService.getCities().body()
            resultCities?.let {
                insertCitiesToRoom(it)
            }
        }
        return resultCities
    }

    private suspend fun insertCitiesToRoom(cities: List<City>) {
        weatherDao.insertCities(cities)
    }
}