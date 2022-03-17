package com.example.exampleest.ui.main.viewmodel

import com.example.exampleest.data.model.City
import com.example.exampleest.data.response.WeatherResponse
import com.example.exampleest.ui.MainApplication
import com.example.exampleest.ui.base.BaseViewModel
import com.example.exampleest.utils.StateLiveData

class MainViewModel : BaseViewModel() {
    private val repository: WeatherResponse = WeatherResponse(MainApplication.appContext)
    val liveDataCities: StateLiveData<List<City>> = StateLiveData()

    suspend fun getCities() {
        val result = repository.getCities()
        if (result.isNullOrEmpty()) {
            liveDataCities.postError(null)
        } else {
            liveDataCities.postSuccess(result)
        }
    }
}