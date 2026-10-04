package com.tech.feature.spreadsheet.configuration.presentation.screen

sealed interface ConfigurationScreenIntent {

    data object CreateTable : ConfigurationScreenIntent

    data class UpdateColumnValue(
        val value: String
    ) : ConfigurationScreenIntent

    data class UpdateRowValue(
        val value: String
    ) : ConfigurationScreenIntent
}