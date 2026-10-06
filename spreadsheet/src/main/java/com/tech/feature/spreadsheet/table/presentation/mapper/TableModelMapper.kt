package com.tech.feature.spreadsheet.table.presentation.mapper

import com.tech.feature.spreadsheet.table.domain.model.CellDto
import com.tech.feature.spreadsheet.table.domain.model.TableDto
import com.tech.feature.spreadsheet.table.presentation.model.Cell
import com.tech.feature.spreadsheet.table.presentation.model.Table
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TableModelMapper
@Inject constructor() {

    fun mapToTable(tableDto: TableDto): Table {
        return Table(
            rows = tableDto.rows.map { row ->
                row.map { cell ->
                    mapToCell(cell)
                }
            }
        )
    }

    private fun mapToCell(cellDto: CellDto): Cell {
        return Cell(
            rowId = cellDto.rowId,
            columnId = cellDto.columnId,
            value = cellDto.value,
            isEditable = false,
            isSelected = false,
        )
    }
}