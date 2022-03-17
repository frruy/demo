package com.example.exampleest.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exampleest.data.model.City

@Database(entities = [City::class], version = 1)
abstract class WeatherDataBase : RoomDatabase() {
    abstract fun WeatherDao(): WeatherDao

    companion object {
        // Singleton prevents multiple
        // instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: WeatherDataBase? = null

        fun getInstance(context: Context): WeatherDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDataBase::class.java,
                    "weather_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}