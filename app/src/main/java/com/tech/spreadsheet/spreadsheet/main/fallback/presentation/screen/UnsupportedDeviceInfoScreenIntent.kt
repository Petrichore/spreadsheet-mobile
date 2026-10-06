package com.tech.spreadsheet.spreadsheet.main.fallback.presentation.screen

sealed class UnsupportedDeviceInfoScreenIntent {

    data object NavigateBack : UnsupportedDeviceInfoScreenIntent()
}