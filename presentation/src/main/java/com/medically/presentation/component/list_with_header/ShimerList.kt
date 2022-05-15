package com.medically.presentation.component.list_with_header

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.medically.presentation.component.ShimmerAnimation

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShimmerListWithHeader() {
    ShimmerAnimation { brush ->
        LazyColumn {
            repeat(2) {
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.wrapContentHeight()
                    ) {
                        Divider(
                            Modifier
                                .fillMaxWidth()
                                .heightIn(1.dp)
                        )
                        Column {
                            repeat(4) {
                                Spacer(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(70.dp)
                                        .padding(vertical = 10.dp)
                                        .background(brush = brush, RoundedCornerShape(5.dp))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}