package com.tech.feature.spreadsheet.table.presentation.screen

import com.tech.feature.spreadsheet.table.presentation.model.Cell

sealed interface TableScreenIntent {

    data object NavigateBack : TableScreenIntent

    data class ChangeCellSelectState(
        val cell: Cell,
    ) : TableScreenIntent

    data class ChangeCellEditableState(
        val cell: Cell,
    ) : TableScreenIntent

    data class UpdateCellValue(
        val cell: Cell,
        val value: String,
    ) : TableScreenIntent
}