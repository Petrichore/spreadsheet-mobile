package com.tech.spreadsheet.spreadsheet.main.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tech.spreadsheet.flow.spreadsheetBaseFlow
import com.tech.spreadsheet.navigation.Route
import com.tech.spreadsheet.routes.SpreadsheetRoute
import com.tech.spreadsheet.spreadsheet.main.fallback.presentation.screen.UnsupportedDeviceInfoScreen
import com.tech.spreadsheet.spreadsheet.main.navigation.route.UnsupportedDeviceInfoScreenRoute

@Composable
fun SpreadsheetNavHost(
    navController: NavHostController,
    startDestination: Route = SpreadsheetRoute.TableConfigurationScreen
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(300)) },
    ) {
        composable<UnsupportedDeviceInfoScreenRoute> {
            UnsupportedDeviceInfoScreen()
        }
        spreadsheetBaseFlow()
    }
}