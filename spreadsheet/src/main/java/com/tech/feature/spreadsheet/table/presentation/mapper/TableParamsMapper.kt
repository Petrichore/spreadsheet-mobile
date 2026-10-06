package com.tech.feature.spreadsheet.table.presentation.mapper

import com.tech.feature.spreadsheet.table.domain.model.TableParamsDto
import com.tech.spreadsheet.routes.SpreadsheetRoute
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TableParamsMapper
@Inject constructor() {

    fun mapToDomainModel(route: SpreadsheetRoute.TableScreen): TableParamsDto {
        return TableParamsDto(
            rowNumber = route.rowNumber,
            columnNumber = route.columnNumber,
        )
    }
}