package com.tech.feature.spreadsheet.table.di

import com.tech.feature.spreadsheet.table.data.TableRepositoryImpl
import com.tech.feature.spreadsheet.table.domain.repository.TableRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TableRepositoryModule {

    @Binds
    fun bindRepository(repository: TableRepositoryImpl): TableRepository
}