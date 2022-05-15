package com.medically.presentation.home.component.subjectList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.medically.model.Subject
import com.medically.presentation.component.list_with_header.Header
import com.medically.presentation.component.list_with_header.ListItem


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SubjectsList(subjects: Map<String, List<Subject>>, onSubjectSelected: (Subject) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        subjects.forEach { (year, yearSubjects) ->
            stickyHeader {
                Header(year)
            }

            items(yearSubjects) { subject ->
                ListItem(subject, subject.name, subject.icon, onSubjectSelected)
            }
        }
    }
}