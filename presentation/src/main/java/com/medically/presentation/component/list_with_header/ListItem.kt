package com.medically.presentation.component.list_with_header

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medically.model.Subject
import com.medically.presentation.component.LoadImageWithShimmer
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun <T> ListItem(
    item: T,
    name: String,
    iconUrl: String? = null,
    onItemSelected: (T) -> Unit
) {
    Surface(
        color = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemSelected(item) },
    ) {
        Row(
            Modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            iconUrl?.let {
                LoadImageWithShimmer(
                    imageUrl = iconUrl, modifier = Modifier
                        .size(35.dp, 35.dp)
                        .padding(end = 13.dp)
                )
            }
            Text(name, color = MaterialTheme.colors.onBackground)
        }
    }
}

@Preview
@Composable
fun SubjectListItemPreview() {
    MedicallyTheme {
        ListItem(
            item = Subject(name = "Anatomy", yearName = "First Year"),
            name = "Anatomy",
            iconUrl = null,
            onItemSelected = { })
    }
}
