package com.tech.spreadsheet.spreadsheet.main.fallback.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.tech.spreadsheet.spreadsheet.R
import com.tech.spreadsheet.spreadsheet.main.fallback.presentation.UnsupportedDeviceInfoScreenViewModel

@Composable
fun UnsupportedDeviceInfoScreen(
    modifier: Modifier = Modifier,
    viewModel: UnsupportedDeviceInfoScreenViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(R.string.main_device_unsupported_title),
            textAlign = TextAlign.Center,
            style = TextStyle.Default.copy(fontSize = 28.sp)
        )
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = stringResource(R.string.main_device_unsupported_message),
            textAlign = TextAlign.Center,
            style = TextStyle.Default.copy(fontSize = 20.sp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                viewModel.onIntent(UnsupportedDeviceInfoScreenIntent.NavigateBack)
            },
            modifier = modifier
                .padding(bottom = 20.dp)
                .width(200.dp)
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = MaterialTheme.colorScheme.primary
            ),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.main_device_unsupported_close_app),
                textAlign = TextAlign.Center,
                style = TextStyle.Default.copy(fontSize = 18.sp)
            )
        }
    }
}