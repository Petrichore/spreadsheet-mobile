package com.tech.feature.spreadsheet.table.presentation.model

data class Cell(
    val rowId: Int,
    val columnId: Int,
    val value: String,
    val isEditable: Boolean = false,
    val isSelected: Boolean = false
)