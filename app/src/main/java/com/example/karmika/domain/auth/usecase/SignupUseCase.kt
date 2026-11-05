package com.example.karmika.domain.auth.usecase

import com.example.karmika.domain.auth.repository.AuthRepository
import com.example.karmika.domain.auth.repository.SignupResult
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        name: String,
        email: String,
        phoneNumber: String,
        password: String
    ): Result<SignupResult> {
        return authRepository.signup(
            name = name,
            email = email,
            phoneNumber = phoneNumber,
            password = password
        )
    }
}
