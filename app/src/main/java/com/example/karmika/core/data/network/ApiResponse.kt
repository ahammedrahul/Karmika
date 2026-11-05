
package com.example.karmika.core.data.network

import com.google.gson.JsonElement

/**
 * Standard response envelope returned by the Karmika backend.
 *
 * Example:
 * {
 *   "success": true,
 *   "message": "Health check successful",
 *   "data": {
 *     "status": "ok",
 *     "timestamp": "2026-09-05T01:30:00.000Z"
 *   },
 *   "error": null
 * }
 */
data class ApiResponse<T>(
    val success: Boolean,
    val message: String?,
    val data: T?,
    val error: JsonElement?
)

/**
 * Response data for GET /api/v1/health
 */
data class HealthData(
    val status: String,
    val timestamp: String
)
