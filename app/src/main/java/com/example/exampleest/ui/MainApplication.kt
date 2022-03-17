package com.example.exampleest.ui

import android.app.Application
import android.content.Context

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        application = this
    }

    companion object {
        lateinit var appContext: Context
        lateinit var application: MainApplication
    }
}