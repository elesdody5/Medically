package com.medically.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.medically.core.subjects.filterBySubject
import com.medically.core.subjects.filterByYears
import com.medically.core.subjects.searchSubject
import com.medically.presentation.home.component.CurrentPlayingCard
import com.medically.presentation.home.component.Filters
import com.medically.presentation.home.component.subjectList.ShimmerSubjectList
import com.medically.presentation.home.component.subjectList.SubjectsList

@Composable
fun HomeScreen() {
    val viewModel = viewModel<HomeViewModel>()
    val state by viewModel.state.collectAsState()
    val allSubjects = state.subjects
    val years = allSubjects.keys
    val subjectsList = allSubjects.values.flatten().map { it.name }.distinct()
    Scaffold {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            CurrentPlayingCard(
                Modifier.fillMaxWidth(),
                onValueChanged = viewModel::searchSubject
            )
            Filters(
                years = years.toList(),
                subjects = subjectsList,
                onYearSelected = viewModel::filterByYears,
                onSubjectSelected = viewModel::filterBySubject,
            )
            if (state.isLoading)
                ShimmerSubjectList()

            if (!state.isLoading)
                SubjectsList(subjects = state.filteredSubjects)
        }
    }
}