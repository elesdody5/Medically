package com.medically.presentation.home.component.subjectList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medically.presentation.ui.theme.MedicallyTheme
import java.util.*

@Composable
fun SubjectHeader(year: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().background(color = Color.White)
    ) {
        Text(
            year.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale("en")) else it.toString() },
            color = MaterialTheme.colors.secondaryVariant,
            modifier = Modifier.padding(end = 11.dp)
        )
        Divider(
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
    }
}

@Preview
@Composable
fun SubjectHeaderPreview() {
    MedicallyTheme {
        SubjectHeader(year = "firstYear")
    }
}