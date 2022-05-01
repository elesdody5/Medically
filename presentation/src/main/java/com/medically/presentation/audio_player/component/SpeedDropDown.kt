package com.medically.presentation.audio_player.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SpeedDropDown(changeSpeed: (Float) -> Unit) {
    val options = listOf(1f, 1.5f, 2f, 2.5f, 3f)
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("1x") }
    Surface(
        shape = CircleShape,
        color = MaterialTheme.colors.secondary
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            modifier = Modifier.size(55.dp, 50.dp),
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = {},
                textStyle = TextStyle(fontSize = 14.sp, textAlign = TextAlign.Center),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.secondaryVariant,
                    disabledTextColor = MaterialTheme.colors.secondaryVariant,
                    backgroundColor = MaterialTheme.colors.secondary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        modifier = Modifier.background(Color.White),
                        onClick = {
                            selectedOptionText = "${selectionOption}x"
                            expanded = false
                            changeSpeed(selectionOption)
                        }
                    ) {
                        Text(
                            text = "$selectionOption",
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }
            }
        }
    }
}