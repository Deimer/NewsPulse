package com.testdeymer.newspulse.features.web

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.testdeymer.presentation.R
import com.testdeymer.presentation.components.TopBarCompose
import com.testdeymer.presentation.components.WebViewCompose
import com.testdeymer.presentation.theme.NewsPulseTheme

@Composable
fun WebScreenCompose(
    attributes: WebScreenAttributes
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TopBarCompose(
            modifier = Modifier,
            navigationIcon = R.drawable.ic_back,
            onNavigationClick = attributes.actions.onPrimaryAction
        )
        WebViewCompose(
            url = attributes.url,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWebScreenCompose() {
    NewsPulseTheme {
        WebScreenCompose(
            attributes = WebScreenAttributes(
                url = "https://www.google.com",
                actions = WebScreenActions(
                    onPrimaryAction = { println("Back action clicked") }
                )
            )
        )
    }
}