package com.example.steamy.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.steamy.ui.screens.DetailScreen
import com.example.steamy.ui.screens.HomeScreen
import com.example.steamy.ui.viewmodel.ProductViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val bottomItems = listOf(BottomNavItem.Home)
    val showBottomBarRoutes = listOf(Routes.HOME)
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route
    val showBottomBar = currentRoute in showBottomBarRoutes

    Scaffold(
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
                val vm: ProductViewModel = viewModel()
                HomeScreen(
                    viewModel = vm,
                    onItemClick = { id -> navController.navigate(Routes.detailRoute(id)) },
                    navController = navController
                )
            }


            composable(
                route = Routes.DETAIL,
                arguments = listOf(navArgument("productoId") { type = NavType.IntType })
            ) { backStackEntry ->
                val vm: ProductViewModel = viewModel()
                val productoId = backStackEntry.arguments?.getInt("productoId") ?: -1
                DetailScreen(
                    itemId = productoId,
                    viewModel = vm,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}