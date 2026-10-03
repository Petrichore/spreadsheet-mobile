package com.tech.feature.spreadsheet.destination

import com.tech.mobile.navigation.Destination

sealed interface SpreadsheetDestination: Destination {

    data object ConfigurationScreen : SpreadsheetDestination {
        override val route: String = "configuration"
    }

    data object TableScreen : SpreadsheetDestination {
        override val route: String = "table"
    }
}