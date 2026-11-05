package com.example.karmika.data.auth.di

import com.example.karmika.data.auth.repository.AuthRepositoryImpl
import com.example.karmika.domain.auth.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        implementation: AuthRepositoryImpl
    ): AuthRepository
}
