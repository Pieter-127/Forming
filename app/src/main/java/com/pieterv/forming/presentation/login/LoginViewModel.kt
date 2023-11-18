package com.pieterv.forming.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pieterv.forming.domain.usecase.ValidateLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateLoginUseCaseImpl: ValidateLoginUseCase,
) : ViewModel() {

    var state by mutableStateOf(LoginScreenState())
        private set

    var screenEvent = Channel<ScreenEvent>()
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.UsernameChanged -> {
                state = state.copy(username = event.username)
            }

            is LoginEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is LoginEvent.RememberMeChanged -> {
                state = state.copy(keepLoggedIn = event.keepLoggedIn)
            }

            is LoginEvent.StartRegistration -> {
                viewModelScope.launch {
                    screenEvent.send(ScreenEvent.Registration)
                }
            }

            is LoginEvent.Submit -> {
                submitData()
            }

            LoginEvent.ForgotPassword -> {
                viewModelScope.launch {
                    screenEvent.send(ScreenEvent.ForgotPassword)
                }
            }
        }
    }

    private fun submitData() {
        val errors = validateLoginUseCaseImpl(state.username, state.password)

        if (errors.isEmpty()) {
            state = state.copy(
                usernameError = false,
                passwordError = false,
            )
            viewModelScope.launch {
                screenEvent.send(ScreenEvent.Success)
            }
        } else {
            state = state.copy(
                usernameError = errors.contains(ValidateLoginUseCase.ValidationError.EmptyUsername),
                passwordError = errors.contains(ValidateLoginUseCase.ValidationError.EmptyPassword),
            )
        }
    }

    sealed class ScreenEvent {
        object Success : ScreenEvent()
        object ForgotPassword : ScreenEvent()
        object Registration : ScreenEvent()
    }

}
