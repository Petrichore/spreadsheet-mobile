package com.tech.feature.spreadsheet.table.presentation.screen

sealed interface TableScreenIntent {

    data object NavigateBack : TableScreenIntent
}