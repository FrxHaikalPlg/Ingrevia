package com.frxhaikal_plg.ingrevia.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "ingrevia_preferences")

class UserPreferences(private val context: Context) {

    private val IS_LOGGED_IN_KEY = booleanPreferencesKey(KEY_IS_LOGGED_IN)

    val isLoggedIn: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_LOGGED_IN_KEY] ?: false
        }

    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN_KEY] = isLoggedIn
        }
    }

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
} 