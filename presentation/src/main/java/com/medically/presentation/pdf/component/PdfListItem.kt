package com.medically.presentation.pdf.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.medically.model.Pdf
import com.medically.presentation.R

@Composable
fun PdfListItem(pdf: Pdf) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(pdf.url))
                context.startActivity(intent)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.weight(2f)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_pdf),
                tint = MaterialTheme.colors.primary,
                contentDescription = "pdf",
                modifier = Modifier
                    .padding(end = 8.dp)
            )
            Text(
                pdf.name ?: "",
                color = MaterialTheme.colors.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

//        CircleWithIcon(
//            iconId = R.drawable.ic_baseline_bookmark_border_24,
//            circleSize = 35.dp,
//            circleColor = MaterialTheme.colors.secondary,
//            contentDescription = null
//        )
    }
}