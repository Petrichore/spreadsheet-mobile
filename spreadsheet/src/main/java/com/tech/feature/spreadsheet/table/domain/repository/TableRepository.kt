package com.tech.feature.spreadsheet.table.domain.repository

import com.tech.feature.spreadsheet.table.domain.model.TableDto
import com.tech.feature.spreadsheet.table.domain.model.TableParamsDto

interface TableRepository {

    fun createTable(tableParams: TableParamsDto): TableDto
}