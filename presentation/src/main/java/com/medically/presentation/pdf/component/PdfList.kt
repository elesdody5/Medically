package com.medically.presentation.pdf.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.medically.model.Pdf

@Composable
fun PdfList(pdfs: List<Pdf>) {
    LazyColumn(
        modifier = Modifier.padding(top = 20.dp),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(pdfs) {
            PdfListItem(pdf = it)
        }
    }
}