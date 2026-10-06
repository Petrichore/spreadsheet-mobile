package com.tech.feature.spreadsheet.table.data

import com.tech.feature.spreadsheet.table.domain.model.CellDto
import com.tech.feature.spreadsheet.table.domain.model.TableDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TableFactory
@Inject constructor() {

    fun createTable(
        rowNumber: Int,
        columnNumber: Int,
    ): TableDto {
        val rows = mutableListOf<List<CellDto>>()
        repeat(rowNumber) { rowIndex ->
            val columnsCell = mutableListOf<CellDto>()
            repeat(columnNumber) { columnIndex ->
                val cell = createCell(rowIndex, columnIndex)
                columnsCell.add(cell)
            }
            rows.add(columnsCell)
        }
        return TableDto(rows = rows)
    }

    private fun createCell(rowIndex: Int, columnIndex: Int): CellDto {
        return CellDto(
            rowId = rowIndex,
            columnId = columnIndex,
            value = "R${rowIndex + 1}; C${columnIndex + 1}"
        )
    }
}