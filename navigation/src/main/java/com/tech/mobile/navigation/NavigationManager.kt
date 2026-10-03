package com.tech.mobile.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor() {

    private val _navigationCommand = MutableStateFlow<NavigationCommand>(NavigationCommand.None)
    val navigationCommand: StateFlow<NavigationCommand>
        get() = _navigationCommand.asStateFlow()

    fun navigate(command: NavigationCommand) {
        _navigationCommand.value = command
    }

    fun clear() {
        _navigationCommand.value = NavigationCommand.None
    }
}