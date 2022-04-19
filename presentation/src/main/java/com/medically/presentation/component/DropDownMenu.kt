package com.medically.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.medically.presentation.ui.theme.MedicallyTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownMenu(
    modifier: Modifier = Modifier,
    options: List<String>,
    placeHolder: String,
    onItemSelected: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(placeHolder) }
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
                value = selectedOptionText,
                onValueChange = {},
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                textStyle = TextStyle(fontSize = 14.sp),
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
                            selectedOptionText = selectionOption
                            expanded = false
                            if (selectionOption != placeHolder) {
                                onItemSelected(selectionOption)
                            } else onItemSelected("")
                        }
                    ) {
                        Text(text = selectionOption, color = MaterialTheme.colors.secondaryVariant)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropDownMenuPreview() {
    MedicallyTheme {
        DropDownMenu(
            options = listOf("one", "two", "three").toMutableList(),
            placeHolder = "Level"
        ) {}
    }
}