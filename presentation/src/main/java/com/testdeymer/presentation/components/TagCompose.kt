package com.testdeymer.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.testdeymer.presentation.R
import com.testdeymer.presentation.theme.White80

@Composable
fun TagCompose(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String,
    onClick: () -> Unit = {},
    iconSize: Dp = dimensionResource(id = R.dimen.dimen_32)
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier.size(iconSize),
            tint = White80
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_4)))
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = White80
        )
    }
}

@Preview(showBackground = false)
@Composable
private fun TagComposePreview() {
    MaterialTheme {
        TagCompose(
            icon = Icons.Default.Delete,
            text = "Delete",
            onClick = {},
            modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_16))
        )
    }
}