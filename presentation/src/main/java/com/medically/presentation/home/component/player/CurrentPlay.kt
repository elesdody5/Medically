package com.medically.presentation.home.component.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.medically.presentation.R
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun CurrentPlaying(title: String?, subTitle: String?, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = Color.White,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_audo),
                contentDescription = "audio",
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Column {
                Text(
                    title ?: stringResource(id = R.string.duration_unknown),
                    modifier = Modifier.padding(bottom = 10.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.W700,
                        fontSize = 18.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = subTitle ?: stringResource(id = R.string.duration_unknown),
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.secondaryVariant
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCurrentPlaying() {
    MedicallyTheme {
        CurrentPlaying(
            title = "Introduction",
            subTitle = "Anatomy",
            modifier = Modifier.fillMaxWidth()
        )
    }
}