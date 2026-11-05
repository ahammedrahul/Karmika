package com.example.karmika.core.data.session

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

data class CurrentUserData(
    val fullName: String = "",
    val email: String = "",
    val phone: String = "",
    val role: String? = null,
    val token: String? = null
)

@Singleton
class UserSession @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val _currentUser = MutableStateFlow(loadFromPrefs())
    val currentUser: StateFlow<CurrentUserData> = _currentUser.asStateFlow()

    private fun loadFromPrefs(): CurrentUserData {
        return CurrentUserData(
            fullName = prefs.getString(KEY_FULL_NAME, "") ?: "",
            email = prefs.getString(KEY_EMAIL, "") ?: "",
            phone = prefs.getString(KEY_PHONE, "") ?: "",
            role = prefs.getString(KEY_ROLE, null),
            token = prefs.getString(KEY_TOKEN, null)
        )
    }

    fun updateUser(
        fullName: String? = null,
        email: String? = null,
        phone: String? = null,
        role: String? = null,
        token: String? = null
    ) {
        val current = _currentUser.value
        val updated = current.copy(
            fullName = fullName ?: current.fullName,
            email = email ?: current.email,
            phone = phone ?: current.phone,
            role = role ?: current.role,
            token = token ?: current.token
        )
        _currentUser.value = updated

        prefs.edit().apply {
            putString(KEY_FULL_NAME, updated.fullName)
            putString(KEY_EMAIL, updated.email)
            putString(KEY_PHONE, updated.phone)
            putString(KEY_ROLE, updated.role)
            putString(KEY_TOKEN, updated.token)
        }.apply()
    }

    fun clear() {
        _currentUser.value = CurrentUserData()
        prefs.edit().clear().apply()
    }

    companion object {
        private const val PREFS_NAME = "karmika_user_session"
        private const val KEY_FULL_NAME = "full_name"
        private const val KEY_EMAIL = "email"
        private const val KEY_PHONE = "phone"
        private const val KEY_ROLE = "role"
        private const val KEY_TOKEN = "token"
    }
}
