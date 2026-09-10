package com.example.karmika.data.local

import android.content.Context
import com.example.karmika.core.common.model.UserRole
import com.example.karmika.data.local.model.LocalUser

class LocalUserStorage(
    context: Context
) {

    private val preferences = context.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE
    )

    // --------------------------------------------------
    // SAVE USER
    // --------------------------------------------------

    fun saveUser(user: LocalUser) {

        preferences.edit()
            .putString(KEY_FULL_NAME, user.fullName)
            .putString(KEY_EMAIL, user.email)
            .putString(KEY_PASSWORD, user.password)
            .putString(KEY_ROLE, user.role.name)
            .apply()
    }


    // --------------------------------------------------
    // GET SAVED USER
    // --------------------------------------------------

    fun getUser(): LocalUser? {

        val fullName = preferences.getString(
            KEY_FULL_NAME,
            null
        )

        val email = preferences.getString(
            KEY_EMAIL,
            null
        )

        val password = preferences.getString(
            KEY_PASSWORD,
            null
        )

        val roleName = preferences.getString(
            KEY_ROLE,
            null
        )

        // No user has been registered yet
        if (
            fullName == null ||
            email == null ||
            password == null ||
            roleName == null
        ) {
            return null
        }

        val role = try {
            UserRole.valueOf(roleName)
        } catch (e: IllegalArgumentException) {
            return null
        }

        return LocalUser(
            fullName = fullName,
            email = email,
            password = password,
            role = role
        )
    }


    // --------------------------------------------------
    // VALIDATE LOGIN
    // --------------------------------------------------

    fun validateLogin(
        email: String,
        password: String
    ): Boolean {

        val user = getUser()
            ?: return false

        return user.email.equals(
            email.trim(),
            ignoreCase = true
        ) && user.password == password
    }


    // --------------------------------------------------
    // CHECK WHETHER USER EXISTS
    // --------------------------------------------------

    fun hasUser(): Boolean {

        return getUser() != null
    }


    // --------------------------------------------------
    // CLEAR USER
    // --------------------------------------------------

    fun clearUser() {

        preferences.edit()
            .clear()
            .apply()
    }


    companion object {

        private const val PREFS_NAME =
            "karmika_local_user"

        private const val KEY_FULL_NAME =
            "full_name"

        private const val KEY_EMAIL =
            "email"

        private const val KEY_PASSWORD =
            "password"

        private const val KEY_ROLE =
            "role"
    }
}