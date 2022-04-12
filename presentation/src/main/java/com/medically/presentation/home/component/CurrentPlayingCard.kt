package com.medically.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medically.presentation.R
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun CurrentPlayingCard() {
    Card(shape = RoundedCornerShape(20.dp), backgroundColor = MaterialTheme.colors.surface) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                stringResource(R.string.app_name),
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.primary
            )
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search")
            }
        }
    }
}

@Preview
@Composable
fun PreviewCurrentPlayCard() {
    MedicallyTheme {
        CurrentPlayingCard()
    }
}