package com.tech.spreadsheet.spreadsheet.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tech.spreadsheet.navigation.NavigationCommand
import com.tech.spreadsheet.navigation.NavigationManager
import com.tech.spreadsheet.spreadsheet.main.navigation.SpreadsheetNavHost
import dagger.hilt.android.AndroidEntryPoint
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
            observeNavigationCommands(navController)
            SpreadsheetNavHost(navController = navController)
        }
    }

    private fun observeNavigationCommands(navController: NavHostController) {
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
            .launchIn(lifecycleScope)
    }
}