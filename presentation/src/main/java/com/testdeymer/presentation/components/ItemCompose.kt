package com.testdeymer.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.testdeymer.presentation.R
import com.testdeymer.presentation.models.ItemUiModel
import com.testdeymer.presentation.theme.NewsPulseTheme

@Composable
fun ItemCompose(
    itemUi: ItemUiModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(
                top = dimensionResource(id = R.dimen.dimen_2),
                bottom = dimensionResource(id = R.dimen.dimen_4)
            )
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterStart).padding(
                top = dimensionResource(id = R.dimen.dimen_12),
                bottom = dimensionResource(id = R.dimen.dimen_12)
            )
        ) {
            Text(
                text = itemUi.title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = itemUi.description,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemComposePreview() {
    val simpleItem = ItemUiModel(
        title = "Title",
        description = "Description",
        onClick = {}
    )
    NewsPulseTheme {
        ItemCompose(
            itemUi = simpleItem,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_16))
        )
    }
}