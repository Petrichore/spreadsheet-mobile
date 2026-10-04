package com.tech.spreadsheet.flow

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tech.feature.spreadsheet.configuration.presentation.screen.TableConfigurationScreen
import com.tech.feature.spreadsheet.table.presentation.screen.TableScreen
import com.tech.spreadsheet.routes.SpreadsheetRoute

fun NavGraphBuilder.spreadsheetBaseFlow() {
    composable<SpreadsheetRoute.TableConfigurationScreen> {
        TableConfigurationScreen()
    }

    composable<SpreadsheetRoute.TableScreen> {
        TableScreen()
    }
}