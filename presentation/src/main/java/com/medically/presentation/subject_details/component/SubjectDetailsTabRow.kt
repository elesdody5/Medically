package com.medically.presentation.subject_details.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SubjectDetailsRow(
    modifier: Modifier = Modifier,
    animateToPage: suspend (Int) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var tabIndex by remember { mutableStateOf(0) }

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        tabData.forEachIndexed { index, item ->
            SubjectTab(
                selected = tabIndex == index, onClick = {
                    coroutineScope.launch {
                        tabIndex = index
                        animateToPage(index)
                    }
                },
                titleId = item
            )
        }
    }
}