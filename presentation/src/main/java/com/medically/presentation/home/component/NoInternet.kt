package com.medically.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medically.presentation.R
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun NoInternet(action: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.no_wifi), contentDescription = "noWifi")
        Text(
            stringResource(id = R.string.no_connection_message_title),
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        Text(
            stringResource(id = R.string.no_connection_message_subtitle),
            style = MaterialTheme.typography.h3
        )
        Text(text = stringResource(id = R.string.refresh),
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .clickable { action() }
                .padding(vertical = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNoInternet() {
    MedicallyTheme {
        NoInternet {

        }
    }
}