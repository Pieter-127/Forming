package com.pieterv.forming.domain.usecase

interface ValidateLoginUseCase {

    operator fun invoke(username: String = "", password: String = ""): ArrayList<ValidationError>

    sealed class ValidationError {
        object EmptyUsername : ValidationError()
        object EmptyPassword : ValidationError()
    }
}