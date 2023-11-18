package com.pieterv.forming.domain.usecase

class ValidateLoginUseCaseImpl : ValidateLoginUseCase {

    override operator fun invoke(
        username: String,
        password: String
    ): ArrayList<ValidateLoginUseCase.ValidationError> {
        val errors = arrayListOf<ValidateLoginUseCase.ValidationError>()
        if (username.isEmpty()) {
            errors.add(ValidateLoginUseCase.ValidationError.EmptyUsername)
        }

        if (password.isEmpty()) {
            errors.add(ValidateLoginUseCase.ValidationError.EmptyPassword)
        }

        return errors
    }
}

