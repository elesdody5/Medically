package com.medically.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medically.presentation.R
import com.medically.presentation.component.DropDownMenu
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun Filters(
    years: List<String>,
    subjects: List<String>,
    onYearSelected: (String) -> Unit,
    onSubjectSelected: (String) -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        DropDownMenu(
            modifier = Modifier.weight(1f),
            options = years,
            placeHolder = stringResource(id = R.string.level),
            onItemSelected = onYearSelected
        )
        DropDownMenu(
            modifier = Modifier.weight(1f),
            options = subjects,
            placeHolder = stringResource(id = R.string.course),
            onItemSelected = onSubjectSelected
        )
    }
}

@Preview
@Composable
fun PreviewFilters() {
    MedicallyTheme {
        Filters(
            years = emptyList(),
            subjects = emptyList(),
            onYearSelected = {},
            onSubjectSelected = {}
        )
    }
}
