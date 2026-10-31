package com.andeanculinary.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.andeanculinary.app.ui.navigation.AndeanNavHost
import com.andeanculinary.app.ui.theme.AndeanCulinaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndeanCulinaryTheme {
                AndeanNavHost()
            }
        }
    }
}
