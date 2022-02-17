package com.example.composedepartment.interactor

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

private val Context.userSettingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "user_settings")
private val isDarkThemeEnabledKey = booleanPreferencesKey("is_dark_theme_enabled")

class UserSettingsInteractor(private val context: Context) {

    fun isDarkThemeEnabled(): Flow<Boolean> =
        context.userSettingsDataStore.data
            .map { it[isDarkThemeEnabledKey] ?: false }
            .flowOn(Dispatchers.Default)

    suspend fun setIsDarkThemeEnabled(value: Boolean) {
        withContext(Dispatchers.Default) {
            context.userSettingsDataStore.edit { it[isDarkThemeEnabledKey] = value }
        }
    }
}