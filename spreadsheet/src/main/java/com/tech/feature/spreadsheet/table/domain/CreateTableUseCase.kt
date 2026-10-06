package com.tech.feature.spreadsheet.table.domain

import com.tech.feature.spreadsheet.table.domain.model.TableDto
import com.tech.feature.spreadsheet.table.domain.model.TableParamsDto
import com.tech.feature.spreadsheet.table.domain.repository.TableRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateTableUseCase
@Inject constructor(
    private val repository: TableRepository
) {

    fun invoke(tableParams: TableParamsDto): TableDto {
        return repository.createTable(tableParams)
    }
}