package com.tech.mobile.navigation

sealed interface NavigationCommand {
    data class Forward(
        val destination: Destination
    ) : NavigationCommand

    data object Back : NavigationCommand

    data object None : NavigationCommand
}