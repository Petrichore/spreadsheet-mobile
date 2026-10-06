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
import com.tech.spreadsheet.spreadsheet.main.navigation.SpreadsheetNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

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
                        )
                    }
                }
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
                        navController.popBackStack()
                    }

                    NavigationCommand.None -> Unit
                }
                navigationManager.clear()
            }
            .launchIn(scope)
    }
}