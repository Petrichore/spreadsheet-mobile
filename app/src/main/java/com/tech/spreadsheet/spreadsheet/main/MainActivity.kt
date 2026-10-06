package com.tech.spreadsheet.spreadsheet.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tech.spreadsheet.brandbook.theme.AppTheme
import com.tech.spreadsheet.navigation.NavigationCommand
import com.tech.spreadsheet.navigation.NavigationManager
import com.tech.spreadsheet.navigation.Route
import com.tech.spreadsheet.routes.SpreadsheetRoute
import com.tech.spreadsheet.spreadsheet.main.navigation.SpreadsheetNavHost
import com.tech.spreadsheet.spreadsheet.main.navigation.route.UnsupportedDeviceInfoScreenRoute
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val MIN_SCREEN_WIDTH_DP = 600

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val startDestination = defineStartDestination()
        setScreenOrientation(startDestination)

        setContent {
            val navController = rememberNavController()
            LaunchedEffect(navController) {
                observeNavigationCommands(navController = navController, scope = this)
            }
            AppTheme {
                Scaffold { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        SpreadsheetNavHost(
                            navController = navController,
                            startDestination = startDestination
                        )
                    }
                }
            }
        }
    }

    private fun defineStartDestination(): Route {
        return if (resources.configuration.smallestScreenWidthDp >= MIN_SCREEN_WIDTH_DP) {
            SpreadsheetRoute.TableConfigurationScreen
        } else {
            UnsupportedDeviceInfoScreenRoute
        }
    }

    private fun setScreenOrientation(startDestination: Route) {
        when (startDestination) {
            is SpreadsheetRoute -> {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            }

            else -> {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
            }
        }
    }

    private fun observeNavigationCommands(
        navController: NavHostController,
        scope: CoroutineScope
    ) {
        navigationManager.navigationCommand
            .onEach { command ->
                when (command) {
                    is NavigationCommand.Forward -> {
                        navController.navigate(command.route)
                    }

                    NavigationCommand.Back -> {
                        if (!navController.popBackStack()) {
                            finish()
                        }
                    }

                    NavigationCommand.None -> Unit
                }
                navigationManager.clear()
            }
            .launchIn(scope)
    }
}