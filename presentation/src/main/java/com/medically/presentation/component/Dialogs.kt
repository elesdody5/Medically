package com.medically.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.medically.presentation.R
import com.medically.presentation.ui.theme.MedicallyTheme

@Composable
fun RoundedAlertDialog(
    modifier: Modifier = Modifier,
    title: String,
    buttonAction: () -> Unit,
    text: String,
    dismissAlert: () -> Unit
) {
    AlertDialog(
        onDismissRequest = dismissAlert,
        buttons = {
            ConfirmButton(
                buttonText = stringResource(id = R.string.confirm),
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 40.dp)
                    .fillMaxWidth(),
                onClick = {
                    buttonAction()
                    dismissAlert()
                }
            )

            Text(
                text = stringResource(id = R.string.cancel),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()
                    .clickable { dismissAlert() }
            )
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h5.copy(
                    fontSize = 28.sp
                ),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onBackground
            )
        },
        text = {
            Text(
                text = text,
                style = TextStyle(fontSize = 18.sp),
                color = MaterialTheme.colors.onBackground
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun ConfirmButton(
    buttonText: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick,
        modifier,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
    ) {
        Text(
            buttonText,
            modifier = Modifier
                .padding(vertical = 6.dp),
            textAlign = TextAlign.Center,
            color = Color.White,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAlertDialog() {
    MedicallyTheme {
        RoundedAlertDialog(title = "Title", buttonAction = { /*TODO*/ }, text = "text") {

        }
    }
}
