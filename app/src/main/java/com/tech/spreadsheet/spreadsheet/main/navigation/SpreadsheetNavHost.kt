package com.tech.spreadsheet.spreadsheet.main.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.tech.spreadsheet.flow.spreadsheetBaseFlow
import com.tech.spreadsheet.routes.SpreadsheetRoute

@Composable
fun SpreadsheetNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = SpreadsheetRoute.TableConfigurationScreen,
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(300)) },
    ) {
        spreadsheetBaseFlow()
    }
}