package com.tech.feature.spreadsheet.table.domain.model

data class CellDto(
    val rowId: Int,
    val columnId: Int,
    val value: String,
)