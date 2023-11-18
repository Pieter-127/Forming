package com.pieterv.forming.presentation.login

import com.pieterv.forming.domain.usecase.ValidateLoginUseCase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private lateinit var systemUnderTest: LoginViewModel

    private val mockUseCase = mock<ValidateLoginUseCase>()

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        systemUnderTest = LoginViewModel(mockUseCase)
    }

    @Test
    fun testGivenUsernameChangeEventThenStateUsernameChanges() {
        val expected = "test"
        systemUnderTest.onEvent(LoginEvent.UsernameChanged(expected))
        assertEquals(expected, systemUnderTest.state.username)
    }

    @Test
    fun testGivenPasswordChangeEventThenStatePasswordChanges() {
        val expected = "test"
        systemUnderTest.onEvent(LoginEvent.PasswordChanged(expected))
        assertEquals(expected, systemUnderTest.state.password)
    }

    @Test
    fun testGivenRememberMeChangeEventThenStateRememberMeChanges() {
        val expectedTrue = true
        systemUnderTest.onEvent(LoginEvent.RememberMeChanged(expectedTrue))
        assertEquals(expectedTrue, systemUnderTest.state.keepLoggedIn)

        val expectedFalse = false
        systemUnderTest.onEvent(LoginEvent.RememberMeChanged(expectedFalse))
        assertEquals(expectedFalse, systemUnderTest.state.keepLoggedIn)
    }

    @Test
    fun testGivenStartRegistrationEventReceiveStartRegistration() =
        kotlinx.coroutines.test.runTest {
            systemUnderTest.onEvent(LoginEvent.StartRegistration)

            val result = systemUnderTest.screenEvent.receive()
            assertTrue(result is LoginViewModel.ScreenEvent.Registration)
        }

    @Test
    fun testGivenForgotPasswordEventReceiveForgotPassword() = kotlinx.coroutines.test.runTest {
        systemUnderTest.onEvent(LoginEvent.ForgotPassword)

        val result = systemUnderTest.screenEvent.receive()
        assertTrue(result is LoginViewModel.ScreenEvent.ForgotPassword)
    }

    @Test
    fun testGivenUsernameAndPasswordScreenEventSuccess() = kotlinx.coroutines.test.runTest {
        val validUsername = "test"
        val validPassword = "123456"
        systemUnderTest.onEvent(LoginEvent.UsernameChanged(validUsername))
        systemUnderTest.onEvent(LoginEvent.PasswordChanged(validPassword))
        whenever(mockUseCase(validUsername, validPassword)).thenReturn(arrayListOf())
        systemUnderTest.onEvent(LoginEvent.Submit)

        val result = systemUnderTest.screenEvent.receive()
        assertTrue(result is LoginViewModel.ScreenEvent.Success)
        Assertions.assertFalse(systemUnderTest.state.usernameError)
        Assertions.assertFalse(systemUnderTest.state.passwordError)
    }

    @Test
    fun testGivenInvalidUsernameAndInvalidPasswordScreenEventSuccess() =
        kotlinx.coroutines.test.runTest {
            val invalidUsername = ""
            val invalidPassword = ""
            systemUnderTest.onEvent(LoginEvent.UsernameChanged(invalidUsername))
            systemUnderTest.onEvent(LoginEvent.PasswordChanged(invalidPassword))
            whenever(mockUseCase(invalidUsername, invalidPassword)).thenReturn(
                arrayListOf(
                    ValidateLoginUseCase.ValidationError.EmptyUsername,
                    ValidateLoginUseCase.ValidationError.EmptyPassword
                )
            )
            systemUnderTest.onEvent(LoginEvent.Submit)

            assertTrue(systemUnderTest.state.usernameError)
            assertTrue(systemUnderTest.state.passwordError)
        }

}