package com.example.steamy



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.steamy.navigation.AppNavGraph
import com.example.steamy.ui.screens.HomeScreen
import com.example.steamy.ui.viewmodel.ProductViewModel
import com.example.steamy.ui.theme.SteamyTheme

class MainActivity : ComponentActivity() {

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SteamyTheme {
                // Aquí inicia directamente en HomeScreen
                AppNavGraph()
            }
        }
    }
}