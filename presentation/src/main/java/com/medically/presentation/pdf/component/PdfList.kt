package com.medically.presentation.pdf.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.medically.model.Pdf
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun PdfList(pdfs: List<Pdf>) {
    LazyColumn(
        modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(48.dp),
    ) {
        items(pdfs) {
            PdfListItem(pdf = it)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewPdf() {
    MedicallyTheme {
        PdfList(
            pdfs =
            listOf(
                Pdf("pdf"),
                Pdf("pdf"),
                Pdf("pdf"),
            )
        )
    }
}