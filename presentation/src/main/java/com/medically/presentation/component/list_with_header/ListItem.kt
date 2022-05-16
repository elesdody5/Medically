package com.medically.presentation.component.list_with_header

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medically.model.Subject
import com.medically.presentation.component.LoadImageWithShimmer
import com.medically.presentation.ui.theme.MedicallyTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> ListItem(
    item: T,
    name: String,
    iconUrl: String? = null,
    onItemClicked: (T) -> Unit,
    onItemLongPress: (T) -> Unit,
    selectionState: Boolean = false,
    isSelected: Boolean = false,
) {
    Surface(
        color = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = { onItemClicked(item) },
                onLongClick = { onItemLongPress(item) },
            )
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
            if (selectionState)
                Checkbox(checked = isSelected,
                    modifier = Modifier
                        .height(8.dp)
                        .padding(0.dp),
                    colors = CheckboxDefaults.colors(MaterialTheme.colors.primary),
                    onCheckedChange = {
                        onItemLongPress(item)
                    })
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
            onItemClicked = { },
            onItemLongPress = {})
    }
}
