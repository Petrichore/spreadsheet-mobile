package com.tech.feature.spreadsheet.configuration.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
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

@Composable
fun TableConfigurationScreen(
    viewModel: TableConfigurationViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()

    TableConfigurationContent(
        state = screenState,
        onViewTableClick = {
            viewModel.onIntent(ConfigurationScreenIntent.CreateTable)
        },
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
    onViewTableClick: () -> Unit = {},
    onColumnValueChanged: (String) -> Unit = {},
    onRowValueChanged: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize(),
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
            onViewTableClick = onViewTableClick,
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
        modifier = modifier.width(100.dp),
        value = value,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        label = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = label,
                textAlign = TextAlign.Center,
                style = TextStyle.Default.copy(fontSize = 12.sp)
            )
        },
        onValueChange = {
            onValueChanged.invoke(it)
        },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction
        ),
    )
}

@Composable
private fun ButtonsContent(
    onCreateTableClick: () -> Unit,
    onViewTableClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ApplyConfigButton(
            clickAction = onViewTableClick
        )
        MyTableButton(
            clickAction = onCreateTableClick,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Composable
private fun ApplyConfigButton(
    clickAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = clickAction,
        modifier = modifier
            .width(200.dp)
            .height(56.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text("Create/Apply")
    }
}

@Composable
private fun MyTableButton(
    clickAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = clickAction,
        modifier = modifier
            .width(200.dp)
            .height(56.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Text("Current Table")
    }
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