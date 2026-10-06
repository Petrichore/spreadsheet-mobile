package com.tech.feature.spreadsheet.table.presentation.screen

import com.tech.feature.spreadsheet.table.presentation.model.Table

data class TableScreenState(
    val columnNumber: Int = 0,
    val rowNumber: Int = 0,
    val table: Table? = null
)