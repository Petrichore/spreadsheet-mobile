package com.tech.feature.spreadsheet.table.presentation

import androidx.lifecycle.ViewModel
import com.tech.feature.spreadsheet.table.presentation.screen.TableScreenIntent
import com.tech.mobile.navigation.NavigationCommand
import com.tech.mobile.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TableViewModel
@Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModel() {

    fun onIntent(intent: TableScreenIntent) {
        when (intent) {
            TableScreenIntent.NavigateBack -> navigateBack()
        }
    }

    private fun navigateBack() {
        navigationManager.navigate(NavigationCommand.Back)
    }
}