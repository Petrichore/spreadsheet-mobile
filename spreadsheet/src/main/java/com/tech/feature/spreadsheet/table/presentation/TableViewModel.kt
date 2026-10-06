package com.tech.feature.spreadsheet.table.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.tech.feature.spreadsheet.table.domain.CreateTableUseCase
import com.tech.feature.spreadsheet.table.presentation.mapper.TableModelMapper
import com.tech.feature.spreadsheet.table.presentation.mapper.TableParamsMapper
import com.tech.feature.spreadsheet.table.presentation.model.Cell
import com.tech.feature.spreadsheet.table.presentation.model.Table
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
    private val savedStateHandle: SavedStateHandle,
    private val createTableUseCase: CreateTableUseCase,
    private val tableParamsMapper: TableParamsMapper,
    private val tableModelMapper: TableModelMapper,
) : ViewModel() {

    private val _screenState = MutableStateFlow(TableScreenState())
    val screenState: StateFlow<TableScreenState>
        get() = _screenState.asStateFlow()

    init {
        handleInitParams()
    }

    fun onIntent(intent: TableScreenIntent) {
        when (intent) {
            is TableScreenIntent.UpdateCellValue -> updateCellValue(intent.cell, intent.value)
            is TableScreenIntent.ChangeCellEditableState -> updateCellEditableState(intent.cell)
            is TableScreenIntent.ChangeCellSelectState -> updateCellSelectState(intent.cell)
            TableScreenIntent.NavigateBack -> navigateBack()
        }
    }

    private fun handleInitParams() {
        val tableParams = savedStateHandle.toRoute<SpreadsheetRoute.TableScreen>()
        _screenState.value = _screenState.value.copy(
            columnNumber = tableParams.columnNumber,
            rowNumber = tableParams.rowNumber
        )
        fetchTable(tableParams)
    }

    private fun fetchTable(tableParams: SpreadsheetRoute.TableScreen) {
        val table = createTableUseCase.invoke(
            tableParams = tableParamsMapper.mapToDomainModel(route = tableParams)
        )
        _screenState.value = _screenState.value.copy(
            table = tableModelMapper.mapToTable(table)
        )
    }

    private fun navigateBack() {
        navigationManager.navigate(NavigationCommand.Back)
    }

    private fun updateCellValue(cell: Cell, value: String) {
        _screenState.value.table?.let { table ->
            if (value.length > 1000) return

            val newCell = cell.copy(value = value)
            updateCell(table, cell, newCell)
        }
    }

    private fun updateCellSelectState(cell: Cell) {
        _screenState.value.table?.let { table ->
            val newCell = cell.copy(isSelected = !cell.isSelected)
            updateCell(table, cell, newCell)
        }
    }

    private fun updateCellEditableState(cell: Cell) {
        _screenState.value.table?.let { table ->
            val newCell = cell.copy(isEditable = !cell.isEditable)
            updateCell(table, cell, newCell)
        }
    }

    private fun updateCell(table: Table, oldCell: Cell, newCell: Cell) {
        _screenState.value = _screenState.value.copy(
            table = table.copy(
                rows = table.rows.toMutableList().apply {
                    this[oldCell.rowId] = this[oldCell.rowId].toMutableList().apply {
                        this[oldCell.columnId] = newCell
                    }
                }
            )
        )
    }
}