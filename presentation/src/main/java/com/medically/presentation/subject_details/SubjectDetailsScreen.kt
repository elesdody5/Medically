package com.medically.presentation.subject_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.medically.core.subject_details.bindChapters
import com.medically.presentation.R
import com.medically.presentation.component.TransparentAppBar
import com.medically.presentation.subject_details.component.SubjectDetailsRow
import com.medically.presentation.subject_details.component.tabData

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SubjectDetailsScreen(goBack: () -> Unit) {
    val viewModel = viewModel<SubjectDetailsViewModel>()
    val state by viewModel.state.collectAsState()
    val doctors = state.doctors.map { it?.name ?: "" }.toList()
    val pagerState = rememberPagerState(initialPage = 0)



    Scaffold(
        topBar = {
            TransparentAppBar(
                goBack,
                doctors,
                stringResource(id = R.string.doctor),
                viewModel::bindChapters,
                state.subject?.yearName ?: "",
                state.subject?.name ?: ""
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            SubjectDetailsRow(
                pagerState = pagerState,
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f),
                count = tabData.size
            ) { index ->
                when (index) {
//                    0 -> ChaptersScreen(state.isLoading, state.chapters)
//                    1 -> PdfScreen(state.isLoading, state.pdfs)
                }
            }
        }
    }
}

