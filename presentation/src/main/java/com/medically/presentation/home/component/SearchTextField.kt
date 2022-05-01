package com.medically.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun SearchTextField(onValueChanged: (String) -> Unit, cancelSearch: () -> Unit) {
    var value by remember { mutableStateOf("") }
    BasicTextField(
        cursorBrush = SolidColor(Color.Black),
        value = value,
        onValueChange = {
            value = it
            onValueChanged(it)
        },
        textStyle = TextStyle(color = MaterialTheme.colors.secondaryVariant),
        modifier = Modifier
            .background(
                Color.White,
                RoundedCornerShape(20)
            )
            .width(IntrinsicSize.Min)
            .width(150.dp),
    ) { innerTextField ->
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {
                cancelSearch()
                onValueChanged("")
            }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "",
                    tint = Color.Gray
                )
            }
            innerTextField()
        }
    }
}