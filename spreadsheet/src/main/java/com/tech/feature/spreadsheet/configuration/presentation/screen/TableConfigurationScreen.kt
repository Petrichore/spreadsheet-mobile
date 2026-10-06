package com.tech.feature.spreadsheet.configuration.presentation.screen

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.tech.feature.spreadsheet.R
import com.tech.feature.spreadsheet.configuration.presentation.TableConfigurationViewModel
import com.tech.spreadsheet.brandbook.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TableConfigurationScreen(
    viewModel: TableConfigurationViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()

    TableConfigurationContent(
        state = screenState,
        onCreateTableClick = {
            viewModel.onIntent(ConfigurationScreenIntent.CreateTable)
        },
        onColumnValueChanged = { value ->
            viewModel.onIntent(ConfigurationScreenIntent.UpdateColumnValue(value))
        },
        onRowValueChanged = { value ->
            viewModel.onIntent(ConfigurationScreenIntent.UpdateRowValue(value))
        },
    )
}

@Composable
private fun TableConfigurationContent(
    state: ConfigurationScreenState,
    onCreateTableClick: () -> Unit = {},
    onColumnValueChanged: (String) -> Unit = {},
    onRowValueChanged: (String) -> Unit = {},
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clearFocusOnTap(
                scope = rememberCoroutineScope(),
                focusManager = LocalFocusManager.current,
                keyboardController = LocalSoftwareKeyboardController.current
            ),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        ConfigurationHeader()
        Spacer(modifier = Modifier.weight(1f))

        NumberFieldsContent(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onColumnValueChanged = onColumnValueChanged,
            onRowValueChanged = onRowValueChanged,
            rowValue = state.rowValue,
            columnValue = state.columnValue,
        )
        Spacer(modifier = Modifier.weight(1f))

        ButtonsContent(
            onCreateTableClick = onCreateTableClick,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun ConfigurationHeader(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Title
        Text(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            text = stringResource(R.string.spreadsheet_config_screen_title),
            textAlign = TextAlign.Center,
            style = TextStyle.Default.copy(fontSize = 40.sp)
        )
        // Subtitle
        Text(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .padding(top = 36.dp)
                .fillMaxWidth(),
            text = stringResource(R.string.spreadsheet_config_screen_subtitle),
            textAlign = TextAlign.Center,
            style = TextStyle.Default.copy(fontSize = 28.sp)
        )
    }
}

@Composable
private fun NumberFieldsContent(
    onColumnValueChanged: (String) -> Unit,
    onRowValueChanged: (String) -> Unit,
    rowValue: String,
    columnValue: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Column
        NumberField(
            value = columnValue,
            onValueChanged = onColumnValueChanged,
            label = stringResource(R.string.spreadsheet_placeholder_column),
            imeAction = ImeAction.Next,
        )
        // Row
        NumberField(
            value = rowValue,
            onValueChanged = onRowValueChanged,
            imeAction = ImeAction.Done,
            label = stringResource(R.string.spreadsheet_placeholder_row),
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}


@Composable
private fun NumberField(
    value: String,
    imeAction: ImeAction,
    label: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier.size(
            width = 200.dp,
            height = 112.dp
        ),
        value = value,
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Center,
            fontSize = 28.sp
        ),
        label = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = label,
                textAlign = TextAlign.Center,
                style = TextStyle.Default.copy(fontSize = 28.sp)
            )
        },
        onValueChange = {
            onValueChanged.invoke(it)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction
        ),
    )
}

@Composable
private fun ButtonsContent(
    onCreateTableClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ConfigurationActionButton(
            text = stringResource(R.string.spreadsheet_action_button_create_table),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            clickAction = onCreateTableClick,
        )
    }
}

@Composable
private fun ConfigurationActionButton(
    clickAction: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    colors: ButtonColors,
) {
    Button(
        onClick = clickAction,
        modifier = modifier
            .width(300.dp)
            .height(84.dp),
        shape = RoundedCornerShape(16.dp),
        colors = colors,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            textAlign = TextAlign.Center,
            style = TextStyle.Default.copy(fontSize = 28.sp)
        )
    }
}

private fun Modifier.clearFocusOnTap(
    scope: CoroutineScope,
    focusManager: FocusManager,
    keyboardController: SoftwareKeyboardController?
): Modifier =
    this.pointerInput(Unit) {
        detectTapGestures(
            onTap = {
                scope.launch {
                    keyboardController?.hide()
                    delay(350)
                    focusManager.clearFocus()
                }
            }
        )
    }

@Preview(showBackground = true, device = Devices.PIXEL_TABLET)
@Composable
fun GreetingPreview() {
    AppTheme {
        TableConfigurationContent(
            state = ConfigurationScreenState(),
        )
    }
}