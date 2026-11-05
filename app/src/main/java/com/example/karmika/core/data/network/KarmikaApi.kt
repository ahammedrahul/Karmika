package com.example.karmika.core.data.network

import com.example.karmika.data.auth.remote.request.LoginRequest
import com.example.karmika.data.auth.remote.request.SignupRequest
import com.example.karmika.data.auth.remote.response.LoginData
import com.example.karmika.data.auth.remote.response.SignupData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface KarmikaApi {

    @GET("api/v1/health")
    suspend fun getHealth(): ApiResponse<HealthData>

    @POST("api/v1/auth/signup")
    suspend fun signup(
        @Body request: SignupRequest
    ): ApiResponse<SignupData>

    @POST("api/v1/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): ApiResponse<LoginData>
}