package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import di.appModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }

//Vid 47
fun initKoin() {
    startKoin {
        modules(appModule())
    }.koin
}