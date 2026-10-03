package com.tech.spreadsheet.api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tech.feature.spreadsheet.configuration.presentation.screen.TableConfigurationScreen
import com.tech.feature.spreadsheet.destination.SpreadsheetDestination
import com.tech.feature.spreadsheet.table.presentation.screen.TableScreen

internal fun NavGraphBuilder.baseFlow() {
    composable(
        route = SpreadsheetDestination.ConfigurationScreen.route,
    ) {
        TableConfigurationScreen()
    }

    composable(
        route = SpreadsheetDestination.TableScreen.route
    ) {
        TableScreen()
    }
}