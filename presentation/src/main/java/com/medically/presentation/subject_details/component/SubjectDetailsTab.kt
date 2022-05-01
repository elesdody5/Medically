package com.medically.presentation.subject_details.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SubjectTab(
    selected: Boolean,
    @StringRes titleId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val color: Color = if (selected) MaterialTheme.colors.primary else Color.Unspecified
    val textColor: Color = if (selected) Color.White else Color.Black
    Surface(
        shape = RoundedCornerShape(85.dp),
        color = color,
        modifier = modifier
            .padding(7.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            stringResource(id = titleId),
            style = MaterialTheme.typography.subtitle1,
            fontSize = 16.sp,
            color = textColor,
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp)
        )
    }
}