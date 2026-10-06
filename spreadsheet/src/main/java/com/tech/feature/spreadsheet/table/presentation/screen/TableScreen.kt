package com.tech.feature.spreadsheet.table.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.tech.feature.spreadsheet.table.presentation.TableViewModel
import com.tech.feature.spreadsheet.table.presentation.model.Cell
import com.tech.feature.spreadsheet.table.presentation.model.Table
import com.tech.spreadsheet.brandbook.theme.AppTheme
import com.tech.spreadsheet.brandbook.theme.SelectedGreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TableScreen(
    viewModel: TableViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsState()

    TableScreenContent(
        screenState = screenState,
        onCellValueChanged = { cell, value ->
            viewModel.onIntent(TableScreenIntent.UpdateCellValue(cell, value))
        },
        changeEditableState = { cell ->
            viewModel.onIntent(TableScreenIntent.ChangeCellEditableState(cell))
        },
        changeSelectState = { cell ->
            viewModel.onIntent(TableScreenIntent.ChangeCellSelectState(cell))
        },
    )
}

@Composable
private fun TableScreenContent(
    screenState: TableScreenState,
    changeEditableState: (Cell) -> Unit = { _ -> },
    changeSelectState: (Cell) -> Unit = { _ -> },
    onCellValueChanged: (Cell, String) -> Unit = { _, _ -> },
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        TableHeader(
            modifier = Modifier.padding(top = 20.dp),
            columnNumber = screenState.columnNumber,
            rowNumber = screenState.rowNumber
        )

        screenState.table?.let { table ->
            Table(
                table = table,
                onCellValueChanged = onCellValueChanged,
                changeSelectState = changeSelectState,
                changeEditableState = changeEditableState,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}

@Composable
private fun Table(
    table: Table,
    onCellValueChanged: (Cell, String) -> Unit,
    changeEditableState: (Cell) -> Unit,
    changeSelectState: (Cell) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(table.rows[0].size),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        table.rows.forEachIndexed { index, cells ->
            items(cells) { cell ->
                TableCell(
                    modifier = if (index == 0) Modifier.padding(top = 12.dp) else Modifier,
                    cell = cell,
                    onValueChanged = onCellValueChanged,
                    changeEditableState = changeEditableState,
                    changeSelectState = changeSelectState
                )
            }
        }
    }
}

@Composable
private fun TableHeader(
    modifier: Modifier = Modifier,
    columnNumber: Int,
    rowNumber: Int
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        TableParams(
            modifier = Modifier.padding(start = 20.dp),
            columnNumber = columnNumber,
            rowNumber = rowNumber
        )
    }
}

@Composable
private fun TableParams(
    columnNumber: Int,
    rowNumber: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TableCell(
    modifier: Modifier = Modifier,
    cell: Cell,
    changeEditableState: (Cell) -> Unit,
    changeSelectState: (Cell) -> Unit,
    onValueChanged: (Cell, String) -> Unit,
) {
    val color = if (cell.isSelected) SelectedGreen else Color.White

    val interactionSource = remember { MutableInteractionSource() }

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        unfocusedBorderColor = Color.LightGray,
        focusedBorderColor = Color.LightGray
    )

    BasicTextField(
        value = cell.value,
        onValueChange = {
            onValueChanged.invoke(cell, it)
        },
        modifier = modifier
            .size(
                width = 200.dp,
                height = 112.dp
            )
            .onFocusChanged { focusState ->
                if (!focusState.isFocused && cell.isEditable) changeEditableState(cell)
            }
            .background(color = color)
            .interceptTapGestures(
                cell = cell,
                scope = rememberCoroutineScope(),
                onTap = {
                    changeSelectState.invoke(cell)
                },
                onDoubleTap = {
                    changeEditableState.invoke(cell)
                }
            ),
        readOnly = !cell.isEditable,
        interactionSource = interactionSource,
        textStyle = TextStyle.Default.copy(
            textAlign = TextAlign.Center,
            fontSize = 28.sp
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        singleLine = true
    ) {
        OutlinedTextFieldDefaults.DecorationBox(
            value = cell.value,
            visualTransformation = VisualTransformation.None,
            innerTextField = it,
            enabled = true,
            singleLine = true,
            interactionSource = interactionSource,
            contentPadding =
                OutlinedTextFieldDefaults.contentPadding(top = 8.dp, bottom = 8.dp),
            colors = textFieldColors,
            container = {
                OutlinedTextFieldDefaults.Container(
                    enabled = true,
                    isError = false,
                    colors = textFieldColors,
                    interactionSource = interactionSource,
                    shape = RectangleShape,
                    unfocusedBorderThickness = 2.dp,
                    focusedBorderThickness = 2.dp
                )
            },
        )
    }
}

fun Modifier.interceptTapGestures(
    scope: CoroutineScope,
    cell: Cell,
    onTap: (Cell) -> Unit,
    onDoubleTap: (Cell) -> Unit
): Modifier = this.pointerInput(cell) {
    var lastClickTime = 0L
    val doubleTapTimeout = viewConfiguration.doubleTapTimeoutMillis
    var job: Job? = null
    awaitEachGesture {
        awaitFirstDown(pass = PointerEventPass.Initial)
        val up = waitForUpOrCancellation(pass = PointerEventPass.Initial)

        if (up != null) {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime < doubleTapTimeout) {
                lastClickTime = 0L
                job?.cancel()
                onDoubleTap(cell)
            } else if (!cell.isEditable) {
                lastClickTime = currentTime
                job = scope.launch {
                    delay(doubleTapTimeout)
                    onTap(cell)
                }
            }
        }
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
                table = Table(
                    rows = listOf(
                        listOf(Cell(1, 1, "R1C1"), Cell(1, 2, "R1C2")),
                        listOf(Cell(2, 1, "R2C1"), Cell(2, 2, "R2C2")),
                    )
                )
            ),
        )
    }
}