package com.medically.presentation.home.component.subjectList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.medically.model.Subject


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SubjectsList(subjects: Map<String, List<Subject>>) {
    LazyColumn(
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        subjects.forEach { (year, yearSubjects) ->
            stickyHeader {
                SubjectHeader(year)
            }

            items(yearSubjects) { subject ->
                SubjectListItem(subject)
            }
        }
    }
}