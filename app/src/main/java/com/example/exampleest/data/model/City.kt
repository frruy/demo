package com.example.exampleest.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "City")
data class City(
    @PrimaryKey
    @ColumnInfo(name = "woeid")
    @SerializedName("woeid")
    val woeId: Long,
    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?,
    @SerializedName("location_type")
    @ColumnInfo(name = "location_type")
    val locationType: String?,
    @SerializedName("latt_long")
    @ColumnInfo(name = "latt_long")
    val lattLong: String?
)
