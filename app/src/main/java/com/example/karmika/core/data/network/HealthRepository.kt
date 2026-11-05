package com.example.karmika.core.data.repository

import com.example.karmika.core.data.network.ApiResponse
import com.example.karmika.core.data.network.HealthData
import com.example.karmika.core.data.network.KarmikaApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HealthRepository @Inject constructor(
    private val api: KarmikaApi
) {

    suspend fun checkHealth(): ApiResponse<HealthData> {
        return api.getHealth()
    }
}