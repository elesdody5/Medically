package com.medically.presentation.completed_lectures

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.medically.presentation.completed_lectures.component.CompletedLecturesList
import com.medically.presentation.component.TransparentAppBar

@Composable
fun CompletedLecturesScreen(goBack: () -> Unit) {
    val viewModel = viewModel<CompletedLecturesViewModel>()
    val state by viewModel.state.collectAsState()
    Scaffold(topBar = {
        TransparentAppBar(
            navigationIcon = {
                IconButton(modifier = Modifier, onClick = { goBack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colors.onBackground,
                    )
                }
            },
            title = "",
            subTitle = state.chapter?.name ?: "",
            actions = {
                Text("${state.lectures.size} / ${state.chapter?.lecturesCount}")
            })
    }) {
        CompletedLecturesList(lectures = state.lectures)
    }
}