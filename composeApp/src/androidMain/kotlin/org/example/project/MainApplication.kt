package org.example.project

import android.app.Application
import di.appModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

//Vid 46
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(appModule())
        }
    }


}

