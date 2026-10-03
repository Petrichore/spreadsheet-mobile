package com.tech.spreadsheet.api

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.tech.feature.spreadsheet.destination.SpreadsheetDestination

@Composable
fun SpreadsheetNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = SpreadsheetDestination.ConfigurationScreen.route,
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(300)) },
    ) {
        baseFlow()
    }
}