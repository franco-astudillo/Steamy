package com.example.steamy



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.steamy.ui.screens.HomeScreen
import com.example.steamy.ui.viewmodel.MainViewModel
import com.example.steamy.ui.theme.SteamyTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SteamyTheme {
                // Aquí inicia directamente en HomeScreen
                HomeScreen(viewModel = viewModel)
            }
        }
    }
}