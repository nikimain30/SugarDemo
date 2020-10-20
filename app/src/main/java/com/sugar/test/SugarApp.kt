package com.sugar.test.view

import android.app.Application
import com.sugar.test.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SugarApp : Application() {

    companion object{
        private lateinit var instance: SugarApp
        fun getInstance(): SugarApp = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initTimber()
    }

    private fun initTimber() {
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}