package io.github.backjeff.chucknorrisjokes.data_local.utils

import android.content.Context
import androidx.core.content.edit

class PreferencesHelper(
    context: Context
) {
    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREFERENCES_APP_NAME,
        Context.MODE_PRIVATE
    )

    fun saveBoolean(key: String, data: Boolean) = sharedPreferences.edit().run {
        putBoolean(key, data)
        apply()
    }

    fun getBoolean(key: String) = sharedPreferences.getBoolean(key, false)

    fun saveString(key: String, data: String?) = sharedPreferences.edit().run {
        putString(key, data?.let { it })
        apply()
    }

    fun getString(key: String) = sharedPreferences.run {
        getString(key, null)?.let { it }
    }

    fun saveFloat(key: String, value: Float) = sharedPreferences.edit {
        putFloat(key, value)
    }

    fun getFloat(key: String) = sharedPreferences.getFloat(key, 0f)

    fun deleteKey(key: String) = sharedPreferences.edit {
        remove(key)
    }

    companion object {
        private const val SHARED_PREFERENCES_APP_NAME =
            "io.github.backjeff.chucknorrisjokes"
    }
}