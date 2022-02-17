package com.example.composedepartment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composedepartment.interactor.UserSettingsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userSettingsInteractor: UserSettingsInteractor
) : ViewModel() {

    private val _isDarkTheme: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> get() = _isDarkTheme.asStateFlow()

    init {
        //todo fix first launch theme
        viewModelScope.launch {
            userSettingsInteractor.isDarkThemeEnabled()
                .collect {
                    _isDarkTheme.value = it
                }
        }
    }

    fun toggleDarkTheme(isEnabled: Boolean) {
        _isDarkTheme.value = isEnabled
        viewModelScope.launch {
            userSettingsInteractor.setIsDarkThemeEnabled(isEnabled)
        }
    }
}