package com.pieterv.forming.di

import com.pieterv.forming.domain.usecase.ValidateLoginUseCase
import com.pieterv.forming.domain.usecase.ValidateLoginUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideLoginUseCase(): ValidateLoginUseCase {
        return ValidateLoginUseCaseImpl()
    }

}
