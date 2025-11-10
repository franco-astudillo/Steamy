package com.example.steamy.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.steamy.ui.screens.CartScreen
import com.example.steamy.ui.screens.DetailScreen
import com.example.steamy.ui.screens.HomeScreen
import com.example.steamy.ui.viewmodel.CartViewModel
import com.example.steamy.ui.viewmodel.ProductViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val productViewModel: ProductViewModel = viewModel()
    val cartViewModel: CartViewModel = viewModel()
    val bottomItems = listOf(BottomNavItem.Home, BottomNavItem.Cart)
    val showBottomBarRoutes = listOf(Routes.HOME, Routes.CART)
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route
    val showBottomBar = currentRoute in showBottomBarRoutes

    Scaffold(
        containerColor = Color(0xFF1E2124),
        bottomBar = {
            if (showBottomBar) BottomBar(navController = navController, items = bottomItems)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(innerPadding)
        ) {
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