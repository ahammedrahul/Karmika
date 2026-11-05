package com.example.karmika.data.auth.repository

import android.util.Log
import com.example.karmika.core.common.model.UserRole
import com.example.karmika.core.data.network.KarmikaApi
import com.example.karmika.data.auth.remote.request.SignupRequest
import com.example.karmika.domain.auth.repository.AuthRepository
import com.example.karmika.domain.auth.repository.SignupResult
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.HttpException

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val api: KarmikaApi
) : AuthRepository {

    override suspend fun signup(
        name: String,
        email: String,
        phoneNumber: String,
        password: String
    ): Result<SignupResult> {

        return runCatching {
            val response = api.signup(
                SignupRequest(
                    name = name.trim(),
                    email = email.trim(),
                    phoneNumber = phoneNumber.trim(),
                    password = password
                )
            )

            if (!response.success) {
                throw IllegalStateException(
                    response.message ?: "Signup failed"
                )
            }

            val user = response.data?.user
                ?: throw IllegalStateException(
                    response.message
                        ?: "Signup response did not contain a user"
                )

            val role = user.role?.let { roleName ->
                runCatching {
                    UserRole.valueOf(roleName.uppercase())
                }.getOrNull()
            }

            SignupResult(
                userId = user.id,
                name = user.name,
                email = user.email,
                phoneNumber = user.phoneNumber,
                role = role,
                onboardingCompleted = user.onboardingCompleted
            )
        }.onFailure { error ->
            if (error is HttpException) {
                val errorBody = error.response()
                    ?.errorBody()
                    ?.string()

                Log.e(
                    "Signup",
                    "HTTP ${error.code()} error: $errorBody",
                    error
                )
            } else {
                Log.e(
                    "Signup",
                    "Signup failed: ${error.message}",
                    error
                )
            }
        }
    }
}