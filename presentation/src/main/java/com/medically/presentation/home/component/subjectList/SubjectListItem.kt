package com.medically.presentation.home.component.subjectList

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.*
import coil.decode.DataSource
import coil.disk.DiskCache
import coil.imageLoader
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.medically.model.Subject
import com.medically.presentation.R
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun SubjectListItem(subject: Subject) {
    Surface(
        color = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(subject.icon)
                    .crossfade(true)
                    .crossfade(300)
                    .allowHardware(true)
                    .build(),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(35.dp, 35.dp)
                    .padding(end = 13.dp),
                contentDescription = null
            )
            Text(subject.name)
        }
    }
}

@Preview
@Composable
fun SubjectListItemPreview() {
    MedicallyTheme {
        SubjectListItem(subject = UISubject(name = "Anatomy", yearName = "First Year"))
    }
}

data class UISubject(
    override val id: String = "",
    override val name: String,
    override val yearName: String,
    override val icon: String? = "https://firebasestorage.googleapis.com/v0/b/medrecord-783c6.appspot.com/o/yearsIcons%2Fanatomy.png?alt=media&token=f7b23726-b66e-4ff0-b5be-09aafeb6344d"
) : Subject()