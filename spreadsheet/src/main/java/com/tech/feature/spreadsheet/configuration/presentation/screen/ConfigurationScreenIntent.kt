package com.tech.feature.spreadsheet.configuration.presentation.screen

sealed interface ConfigurationScreenIntent {

    data object CreateTable : ConfigurationScreenIntent

    data object OpenTable : ConfigurationScreenIntent
}