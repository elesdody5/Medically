package com.medically.presentation.subject_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.medically.core.subject_details.bindDoctorMaterials
import com.medically.presentation.R
import com.medically.presentation.chapters.ChaptersScreen
import com.medically.presentation.component.DropDownMenu
import com.medically.presentation.component.TransparentAppBar
import com.medically.presentation.pdf.PdfScreen
import com.medically.presentation.subject_details.component.SubjectDetailsRow
import com.medically.presentation.subject_details.component.tabData
import com.medically.presentation.video.VideoScreen

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SubjectDetailsScreen(goBack: () -> Boolean, navigateToLectures: () -> Unit) {
    val viewModel = viewModel<SubjectDetailsViewModel>()
    val state by viewModel.state.collectAsState()
    val doctors = state.doctors.map { it?.name ?: "" }.toList()
    val pagerState = rememberPagerState(initialPage = 0)
    Scaffold(
        topBar = {
            TransparentAppBar(
                state.subject?.yearName ?: "",
                state.subject?.name ?: "",
                navigationIcon = {
                    IconButton(modifier = Modifier, onClick = { goBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colors.onBackground,
                        )
                    }
                },
                actions = {
                    DropDownMenu(
                        modifier = Modifier.width(160.dp),
                        options = doctors,
                        placeHolder = stringResource(id = R.string.doctor),
                        onItemSelected = viewModel::bindDoctorMaterials
                    )
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            SubjectDetailsRow { index -> pagerState.animateScrollToPage(index) }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f),
                count = tabData.size,
                userScrollEnabled = false
            ) { index ->
                when (index) {
                    0 -> ChaptersScreen(state.isLoading, state.chapters, navigateToLectures)
                    1 -> PdfScreen(state.isLoading, state.pdfs)
                    2 -> VideoScreen(state.isLoading, state.videos)
                }
            }
        }
    }
}

