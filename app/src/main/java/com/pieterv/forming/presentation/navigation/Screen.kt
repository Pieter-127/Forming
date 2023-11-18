package com.pieterv.forming.presentation.navigation

sealed class Screen(
    val route: String
) {
    object LoginScreen : Screen("login_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}