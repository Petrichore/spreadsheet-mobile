package com.tech.feature.spreadsheet.configuration.presentation

import androidx.lifecycle.ViewModel
import com.tech.feature.spreadsheet.configuration.presentation.screen.ConfigurationScreenIntent
import com.tech.feature.spreadsheet.configuration.presentation.screen.ConfigurationScreenState
import com.tech.feature.spreadsheet.destination.SpreadsheetDestination
import com.tech.mobile.navigation.NavigationCommand
import com.tech.mobile.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

private const val MAX_COLUMN = 6
private const val MIN_COLUMN = 1
private const val MAX_ROW = 1000
private const val MIN_ROW = 1

@HiltViewModel
class TableConfigurationViewModel
@Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModel() {

    private val _screenState = MutableStateFlow(
        ConfigurationScreenState()
    )
    val screenState: StateFlow<ConfigurationScreenState>
        get() = _screenState.asStateFlow()

    fun onIntent(intent: ConfigurationScreenIntent) {
        when (intent) {
            is ConfigurationScreenIntent.UpdateColumnValue -> updateColumnValue(intent.value)
            is ConfigurationScreenIntent.UpdateRowValue -> updateRowValue(intent.value)
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

    private fun updateColumnValue(value: String) {
        if (value.isEmpty()) {
            _screenState.value = screenState.value.copy(columnValue = value)
            return
        }
        val newValue = value.toIntOrNull()
        if (newValue != null && newValue in 1..MAX_COLUMN) {
            _screenState.value = screenState.value.copy(columnValue = newValue.toString())
        }
    }

    private fun updateRowValue(value: String) {
        if (value.isEmpty()) {
            _screenState.value = screenState.value.copy(rowValue = value)
            return
        }
        val newValue = value.toIntOrNull()
        if (newValue != null && newValue in 1..MAX_ROW) {
            _screenState.value = screenState.value.copy(rowValue = newValue.toString())
        }
    }
}