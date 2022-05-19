package com.medically.presentation.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.medically.model.Video
import com.medically.presentation.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VideosDropDownMenu(
    modifier: Modifier = Modifier,
    options: List<Video>,
    onItemSelected: (Video) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var currentSelected by remember(options) { mutableStateOf(options.firstOrNull()) }
    val uriHandler = LocalUriHandler.current
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(45.dp),
        color = MaterialTheme.colors.secondary
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                readOnly = true,
                value = currentSelected?.name ?: "",
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {},
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },

                textStyle = TextStyle(fontSize = 14.sp, textAlign = TextAlign.Start),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.secondaryVariant,
                    disabledTextColor = MaterialTheme.colors.secondaryVariant,
                    backgroundColor = MaterialTheme.colors.secondary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                maxLines = 1,
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEach { selected ->
                    DropdownMenuItem(
                        modifier = Modifier.background(Color.White),
                        onClick = {
                            currentSelected = selected
                            expanded = false
                            onItemSelected(selected)
                        }
                    ) {
                        Text(
                            text = selected.name ?: "",
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }

                DropdownMenuItem(
                    modifier = Modifier.background(Color.White),
                    onClick = {
                        expanded = false
                        currentSelected?.url?.let { uriHandler.openUri(it) }
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.watch_on_youtube),
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }
}
