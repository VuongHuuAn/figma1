package com.example.figma1

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Tokens : Screen("tokens")
    object Swap : Screen("swap")
    object Browser : Screen("browser")
    object Asset : Screen("asset")
    object History : Screen("history")

    // Additional screens

}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToHistory = {
                    navController.navigate(Screen.History.route)
                }
            )
        }

        composable(Screen.Tokens.route) {
            TokensScreen()
        }

        composable(Screen.Swap.route) {
            SwapScreen()
        }

        composable(Screen.Browser.route) {
            BrowserScreen()
        }

        composable(Screen.Asset.route) {
            AssetScreen()
        }

        composable(Screen.History.route) {
            HistoryScreen(
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }


}

