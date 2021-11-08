package com.example.lab2

import android.app.Application
import timber.log.Timber

class Lab2test : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
