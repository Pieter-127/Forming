package com.pieterv.forming.presentation.login

sealed class LoginEvent {
    data class UsernameChanged(val username: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data class RememberMeChanged(val keepLoggedIn: Boolean) : LoginEvent()

    object Submit : LoginEvent()
    object ForgotPassword : LoginEvent()
    object StartRegistration : LoginEvent()
}