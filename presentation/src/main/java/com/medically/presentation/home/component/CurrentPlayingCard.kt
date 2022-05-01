package com.medically.presentation.home.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.medically.presentation.R
import com.medically.presentation.home.component.player.CurrentPlaying
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun CurrentPlayingCard(
    modifier: Modifier = Modifier,
    title: String?,
    subTitle: String?,
    onValueChanged: (String) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(vertical = 40.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    stringResource(R.string.app_name),
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.primary
                )
                AnimatedVisibility(visible = isExpanded) {
                    SearchTextField(onValueChanged) { isExpanded = false }
                }
                if (!isExpanded)
                    IconButton(onClick = { isExpanded = !isExpanded }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            tint = Color.Black,
                            contentDescription = "search"
                        )
                    }
            }
            Text(
                stringResource(id = R.string.continue_listen),
                style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colors.onBackground)
            )
            CurrentPlaying(
                title = title,
                subTitle = subTitle,
                Modifier
                    .fillMaxWidth()
                    .padding(top = 9.dp, bottom = 20.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewCurrentPlayCard() {
    MedicallyTheme {
        CurrentPlayingCard(title = "", subTitle = "") {}
    }
}