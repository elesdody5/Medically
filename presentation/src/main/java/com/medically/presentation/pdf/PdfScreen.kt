package com.medically.presentation.pdf

import androidx.compose.runtime.Composable
import com.medically.model.Pdf
import com.medically.presentation.R
import com.medically.presentation.component.EmptyTextMessage
import com.medically.presentation.component.LoadingProgress
import com.medically.presentation.pdf.component.PdfList

@Composable
fun PdfScreen(isLoading: Boolean, pdfs: List<Pdf>) {
    if (isLoading)
        LoadingProgress()
    if (pdfs.isEmpty() && !isLoading)
        EmptyTextMessage(iconId = R.drawable.ic_pdf, message = R.string.no_pdf)
    if (!isLoading)
        PdfList(pdfs = pdfs)
}