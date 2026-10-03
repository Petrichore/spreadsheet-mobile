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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
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
    TableConfigurationContent(
        onViewTableClick = {
            viewModel.onIntent(ConfigurationScreenIntent.CreateTable)
        },
        onCreateTableClick = {
            viewModel.onIntent(ConfigurationScreenIntent.CreateTable)
        }
    )
}

@Composable
private fun TableConfigurationContent(
    onCreateTableClick: () -> Unit = {},
    onViewTableClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        ConfigurationHeader()
        Spacer(modifier = Modifier.weight(1f))

        NumberFieldsContent(
            modifier = Modifier.align(Alignment.CenterHorizontally)
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
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        NumberField(
            value = "20",
            label = "Columns"
        )
        NumberField(
            value = "20",
            label = "Rows",
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}


@Composable
private fun NumberField(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = {},
        label = {
            Text(
                label,
                textAlign = TextAlign.Center,
            )
        },
        modifier = modifier,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
        TableConfigurationContent()
    }
}