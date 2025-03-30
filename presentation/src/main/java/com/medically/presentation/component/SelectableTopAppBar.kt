package com.medically.presentation.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable

@Composable
fun topAppBar(
    title: String,
    selectionState: Boolean,
    selectedCount: Int,
    clearSelection: () -> Unit,
    deleteSelected: () -> Unit
): @Composable () -> Unit {
    return if (selectionState) {
        {
            TransparentAppBar(
                title = "",
                navigationIcon = {
                    IconButton(onClick = clearSelection) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = ""
                        )
                    }
                },
                subTitle = selectedCount.toString(),
                actions = {
                    IconButton(onClick = deleteSelected) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                    }
                })
        }
    } else {
        {
            TransparentAppBar(
                navigationIcon = null,
                title = "",
                subTitle = title,
            )
        }
    }
}