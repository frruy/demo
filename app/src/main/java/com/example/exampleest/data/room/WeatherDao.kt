package com.example.exampleest.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exampleest.data.model.City

@Dao
interface WeatherDao {
    @Query("SELECT * FROM City")
    suspend fun getCities(): List<City>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(cities: List<City>)
}