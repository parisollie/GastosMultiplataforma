package org.example.project


import CrossConfigDevice
import androidx.compose.ui.window.ComposeUIViewController
import com.exampleApp.db.AppDatabase
import data.DatabaseDriverFactory
import di.appModule
import org.koin.core.context.startKoin



fun MainViewController() = ComposeUIViewController { App(CrossConfigDevice()) }



//Vid 47
fun initKoin() {
    startKoin {
        //Vid 52
        modules(appModule(AppDatabase.invoke(DatabaseDriverFactory().createDriver())))
    }.koin
}