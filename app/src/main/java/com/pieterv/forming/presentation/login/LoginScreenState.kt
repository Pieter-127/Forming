package com.pieterv.forming.presentation.login

data class LoginScreenState(
    val username: String = "",
    val usernameError: Boolean = false,
    val password: String = "",
    val passwordError: Boolean = false,
    val keepLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
)