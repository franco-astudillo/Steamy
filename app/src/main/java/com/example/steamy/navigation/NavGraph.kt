package com.example.steamy.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.steamy.ui.screens.*
import com.example.steamy.ui.viewmodel.AuthViewModel
import com.example.steamy.ui.viewmodel.CartViewModel
import com.example.steamy.ui.viewmodel.ProductViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val productViewModel: ProductViewModel = viewModel()
    val cartViewModel: CartViewModel = viewModel()
    val authViewModel: AuthViewModel = viewModel()

    val bottomItems = listOf(BottomNavItem.Home, BottomNavItem.Cart)
    val showBottomBarRoutes = listOf(Routes.HOME, Routes.CART)
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route
    val showBottomBar = currentRoute in showBottomBarRoutes

    val isAuthenticated = FirebaseAuth.getInstance().currentUser?.isEmailVerified == true

    Scaffold(
        containerColor = Color(0xFF1E2124),
        bottomBar = {
            if (showBottomBar) BottomBar(navController = navController, items = bottomItems)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if (isAuthenticated) Routes.HOME else Routes.LOGIN,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.LOGIN) {
                LoginScreen(
                    onLogin = { email, password ->
                        authViewModel.login(email, password)
                    },
                    onNavigateToRegister = {
                        navController.navigate(Routes.REGISTER)
                    },
                    onSuccess = {
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.LOGIN) { inclusive = true }
                        }
                    },
                    authViewModel = authViewModel
                )
            }


            composable(Routes.HOME) {
                HomeScreen(
                    viewModel = productViewModel,
                    cartViewModel = cartViewModel,
                    onItemClick = { id -> navController.navigate(Routes.detailRoute(id)) },
                    navController = navController
                )
            }

            composable(
                route = Routes.DETAIL,
                arguments = listOf(navArgument("productoId") { type = NavType.IntType })
            ) { backStackEntry ->
                val productoId = backStackEntry.arguments?.getInt("productoId") ?: -1
                DetailScreen(
                    itemId = productoId,
                    viewModel = productViewModel,
                    onBack = { navController.popBackStack() }
                )
            }

            composable(Routes.CART) {
                CartScreen(cartViewModel = cartViewModel, navController = navController)
            }
        }
    }
}