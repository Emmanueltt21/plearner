package com.kottland.pleaner

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class PleanerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialization code can go here if needed
    }
}
