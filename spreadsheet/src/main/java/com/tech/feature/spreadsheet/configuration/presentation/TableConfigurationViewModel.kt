package com.tech.feature.spreadsheet.configuration.presentation

import androidx.lifecycle.ViewModel
import com.tech.feature.spreadsheet.configuration.presentation.screen.ConfigurationScreenIntent
import com.tech.feature.spreadsheet.destination.SpreadsheetDestination
import com.tech.mobile.navigation.NavigationCommand
import com.tech.mobile.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TableConfigurationViewModel
@Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModel() {

    fun onIntent(intent: ConfigurationScreenIntent) {
        when (intent) {
            ConfigurationScreenIntent.CreateTable
                -> performNavigationForward(SpreadsheetDestination.TableScreen)

            ConfigurationScreenIntent.OpenTable
                -> performNavigationForward(SpreadsheetDestination.TableScreen)
        }
    }

    private fun performNavigationForward(destination: SpreadsheetDestination) {
        navigationManager.navigate(
            NavigationCommand.Forward(destination)
        )
    }
}