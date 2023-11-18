package com.pieterv.forming.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pieterv.forming.presentation.login.LoginScreen
import com.pieterv.forming.presentation.login.LoginViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(
                navController = navController,
                viewModel = loginViewModel
            )
        }
    }
}