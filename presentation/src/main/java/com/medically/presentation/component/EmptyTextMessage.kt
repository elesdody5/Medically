package com.medically.presentation.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medically.presentation.R
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun EmptyTextMessage(
    @DrawableRes iconId: Int,
    @StringRes message: Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = "empty_icon",
            Modifier.size(100.dp, 100.dp),
        )
        Text(
            stringResource(id = message),
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3.copy(color = MaterialTheme.colors.onBackground)
        )
    }
}

@Preview
@Composable
fun PreviewEmptyMessage() {
    MedicallyTheme {
        EmptyTextMessage(iconId = R.drawable.ic_audo, message = R.string.no_audio)
    }
}