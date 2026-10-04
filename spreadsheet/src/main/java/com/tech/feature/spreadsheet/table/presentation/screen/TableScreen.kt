package com.tech.feature.spreadsheet.table.presentation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.tech.feature.spreadsheet.table.presentation.TableViewModel
import com.tech.spreadsheet.brandbook.theme.AppTheme

@Composable
fun TableScreen(
    viewModel: TableViewModel = hiltViewModel()
) {
//    BackHandler {
//        viewModel.onIntent(TableScreenIntent.NavigateBack)
//    }

    val screenState by viewModel.screenState.collectAsState()
    TableScreenContent(
        screenState = screenState
    )
}

@Composable
private fun TableScreenContent(
    screenState: TableScreenState,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(2f))
        TableHeader()
        Spacer(modifier = Modifier.weight(0.5f))
        TableParams(
            columnNumber = screenState.columnNumber,
            rowNumber = screenState.rowNumber
        )
        Spacer(modifier = Modifier.weight(3f))
    }
}

@Composable
private fun TableHeader(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        text = "Table would be displayed here",
        textAlign = TextAlign.Center,
        style = TextStyle.Default.copy(fontSize = 40.sp)
    )
}

@Composable
private fun TableParams(
    columnNumber: Int,
    rowNumber: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        // Column number
        Text(
            text = "Column: $columnNumber",
            textAlign = TextAlign.Center,
            style = TextStyle.Default.copy(fontSize = 40.sp)
        )
        // Row number
        Text(
            modifier = Modifier.padding(start = 28.dp),
            text = "Row: $rowNumber",
            textAlign = TextAlign.Center,
            style = TextStyle.Default.copy(fontSize = 40.sp)
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_TABLET)
@Composable
fun GreetingPreview() {
    AppTheme {
        TableScreenContent(
            screenState = TableScreenState(
                columnNumber = 5,
                rowNumber = 10,
            ),
        )
    }
}