package com.pieterv.forming.domain.usecase

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ValidateLoginUseCaseImplTest {

    private lateinit var systemUnderTest: ValidateLoginUseCaseImpl

    @BeforeEach
    fun setUp() {
        systemUnderTest = ValidateLoginUseCaseImpl()
    }

    @Test
    fun testGivenUsernameEmptyReturnError() {
        val expected = arrayListOf(ValidateLoginUseCase.ValidationError.EmptyUsername)
        val result = ValidateLoginUseCaseImpl().invoke("", "123456")

        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testGivenPasswordEmptyReturnError() {
        val expected = arrayListOf(ValidateLoginUseCase.ValidationError.EmptyPassword)
        val result = ValidateLoginUseCaseImpl().invoke(username = "Test", password = "")

        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testGivenUsernameEmptyAndPasswordEmptyReturnErrors() {
        val expected = arrayListOf(
            ValidateLoginUseCase.ValidationError.EmptyUsername,
            ValidateLoginUseCase.ValidationError.EmptyPassword
        )
        val result = ValidateLoginUseCaseImpl().invoke("", "")

        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testUseCaseDefaultsToEmptyAndReturnErrors() {
        val expected = arrayListOf(
            ValidateLoginUseCase.ValidationError.EmptyUsername,
            ValidateLoginUseCase.ValidationError.EmptyPassword
        )
        val result = ValidateLoginUseCaseImpl().invoke()

        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testGivenUsernameAndPasswordReturnNoErrors() {
        val expected = arrayListOf<ValidateLoginUseCase.ValidationError>()
        val result = ValidateLoginUseCaseImpl().invoke("Test", "123456")

        Assertions.assertEquals(expected, result)
    }
}