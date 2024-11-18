package com.testdeymer.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.testdeymer.presentation.theme.NewsPulseTheme

@Composable
fun LinkTextCompose(
    modifier: Modifier = Modifier,
    text: String = "",
    linkText: String,
    onClick: () -> Unit
) {
    val combinedText = buildAnnotatedString {
        if(text.isNotEmpty()) append(text)
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(linkText)
        }
    }
    Text(
        text = combinedText,
        style = MaterialTheme.typography.labelLarge.copy(
            color = MaterialTheme.colorScheme.onBackground
        ),
        modifier = modifier.clickable(onClick = onClick)
    )
}

@Preview(showBackground = true)
@Composable
private fun LinkTextPreview() {
    NewsPulseTheme {
        LinkTextCompose(
            text = "Open the link: ",
            linkText = "Link",
            onClick = {
                println("Link opened!")
            }
        )
    }
}