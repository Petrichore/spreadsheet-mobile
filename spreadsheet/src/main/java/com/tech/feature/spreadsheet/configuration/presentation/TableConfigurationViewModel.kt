package com.tech.feature.spreadsheet.configuration.presentation

import androidx.lifecycle.ViewModel
import com.tech.feature.spreadsheet.configuration.presentation.screen.ConfigurationScreenIntent
import com.tech.feature.spreadsheet.configuration.presentation.screen.ConfigurationScreenState
import com.tech.spreadsheet.navigation.NavigationCommand
import com.tech.spreadsheet.navigation.NavigationManager
import com.tech.spreadsheet.navigation.Route
import com.tech.spreadsheet.routes.SpreadsheetRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

private const val MAX_COLUMN = 6
private const val MAX_ROW = 1000

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
            ConfigurationScreenIntent.CreateTable -> handleCreateTableIntent()
        }
    }

    private fun handleCreateTableIntent() {
        val columnNumber = screenState.value.columnValue.toIntOrNull()
        val rowNumber = screenState.value.rowValue.toIntOrNull()

        if (columnNumber != null && rowNumber != null) {
            val route = SpreadsheetRoute.TableScreen(
                columnNumber = columnNumber,
                rowNumber = rowNumber,
            )
            performNavigationForward(route)
        }
    }

    private fun performNavigationForward(route: Route) {
        navigationManager.navigate(
            NavigationCommand.Forward(route)
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