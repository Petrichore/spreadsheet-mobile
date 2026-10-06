package com.tech.spreadsheet.routes

import com.tech.spreadsheet.navigation.Route
import kotlinx.serialization.Serializable

sealed class SpreadsheetRoute : Route {

    @Serializable
    data class TableScreen(
        val columnNumber: Int,
        val rowNumber: Int,
    ) : SpreadsheetRoute()

    @Serializable
    data object TableConfigurationScreen : SpreadsheetRoute()
}