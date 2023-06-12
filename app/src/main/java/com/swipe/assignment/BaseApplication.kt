package com.swipe.assignment

import android.app.Application
import com.swipe.assignment.di.retrofitBuilderModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                retrofitBuilderModule
            )
            androidContext(this@BaseApplication)
        }
    }
}