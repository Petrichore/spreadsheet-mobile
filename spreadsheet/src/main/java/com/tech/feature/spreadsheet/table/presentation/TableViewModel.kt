package com.tech.feature.spreadsheet.table.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.tech.feature.spreadsheet.table.presentation.screen.TableScreenIntent
import com.tech.feature.spreadsheet.table.presentation.screen.TableScreenState
import com.tech.spreadsheet.navigation.NavigationCommand
import com.tech.spreadsheet.navigation.NavigationManager
import com.tech.spreadsheet.routes.SpreadsheetRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TableViewModel
@Inject constructor(
    private val navigationManager: NavigationManager,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _screenState = MutableStateFlow(TableScreenState())
    val screenState: StateFlow<TableScreenState>
        get() = _screenState.asStateFlow()

    init {
        handleInitParams()
    }

    fun onIntent(intent: TableScreenIntent) {
        when (intent) {
            TableScreenIntent.NavigateBack -> navigateBack()
        }
    }

    private fun handleInitParams() {
        val tableParams = savedStateHandle.toRoute<SpreadsheetRoute.TableScreen>()
        _screenState.value = _screenState.value.copy(
            columnNumber = tableParams.columnNumber,
            rowNumber = tableParams.rowNumber
        )
    }

    private fun navigateBack() {
        navigationManager.navigate(NavigationCommand.Back)
    }
}