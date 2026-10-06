package com.tech.feature.spreadsheet.table.data

import com.tech.feature.spreadsheet.table.domain.model.TableDto
import com.tech.feature.spreadsheet.table.domain.model.TableParamsDto
import com.tech.feature.spreadsheet.table.domain.repository.TableRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TableRepositoryImpl
@Inject constructor(
    private val tableFactory: TableFactory,
) : TableRepository {

    override fun createTable(tableParams: TableParamsDto): TableDto {
        return tableFactory.createTable(
            rowNumber = tableParams.rowNumber,
            columnNumber = tableParams.columnNumber
        )
    }
}