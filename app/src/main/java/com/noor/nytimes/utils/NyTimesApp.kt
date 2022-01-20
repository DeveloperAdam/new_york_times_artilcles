package com.noor.nytimes.utils

import android.app.Application
import android.util.Log
import androidx.multidex.BuildConfig
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Adam Noor on 27/12/2021.
 */

@HiltAndroidApp
class NyTimesApp : Application() {

    override fun onCreate() { super.onCreate() }
}