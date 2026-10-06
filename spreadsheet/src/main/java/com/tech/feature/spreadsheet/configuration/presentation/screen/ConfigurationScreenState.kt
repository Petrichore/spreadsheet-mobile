package com.tech.feature.spreadsheet.configuration.presentation.screen

data class ConfigurationScreenState(
    val columnValue: String = "",
    val rowValue: String = "",
    val isViewTableButtonVisible: Boolean = false
)