package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            //Vid 80
            SetColorStatusBar()
            App()
        }
    }
}

//Vid 80
@Composable
private fun SetColorStatusBar(){
    val systemUiController = rememberSystemUiController()
    val isDarkMode = isSystemInDarkTheme()

    DisposableEffect(systemUiController, isDarkMode) {
        systemUiController.setSystemBarsColor(
            color = if (isDarkMode) Color(0xFF1E1E1E) else Color.White,
            darkIcons = !isDarkMode
        )
        onDispose {}
    }
}

//Vid 27
@Preview(showBackground = true)
@Composable
fun AppAndroidPreview() {
    App()
}