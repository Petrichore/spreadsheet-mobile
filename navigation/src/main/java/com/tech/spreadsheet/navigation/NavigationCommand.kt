package com.tech.spreadsheet.navigation

sealed interface NavigationCommand {
    data class Forward(
        val route: Route
    ) : NavigationCommand

    data object Back : NavigationCommand

    data object None : NavigationCommand
}