package com.tech.spreadsheet.spreadsheet.main.fallback.presentation

import androidx.lifecycle.ViewModel
import com.tech.spreadsheet.navigation.NavigationCommand
import com.tech.spreadsheet.navigation.NavigationManager
import com.tech.spreadsheet.spreadsheet.main.fallback.presentation.screen.UnsupportedDeviceInfoScreenIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UnsupportedDeviceInfoScreenViewModel
@Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModel() {

    fun onIntent(intent: UnsupportedDeviceInfoScreenIntent) {
        when (intent) {
            UnsupportedDeviceInfoScreenIntent.NavigateBack -> performBackNavigation()
        }
    }

    private fun performBackNavigation() {
        navigationManager.navigate(NavigationCommand.Back)
    }
}