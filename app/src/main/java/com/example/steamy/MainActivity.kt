package com.example.steamy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.steamy.navigation.AppNavGraph
import com.example.steamy.ui.theme.SteamyTheme
import com.example.steamy.ui.viewmodel.ProductViewModel
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //  Inicializa Firebase
        FirebaseApp.initializeApp(this)

        setContent {
            SteamyTheme {
                //  Tu navegación ahora puede incluir login/register
                AppNavGraph()
            }
        }
    }
}