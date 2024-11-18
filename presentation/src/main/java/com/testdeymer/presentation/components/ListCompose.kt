package com.testdeymer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.testdeymer.presentation.R
import com.testdeymer.presentation.models.ItemUiModel
import com.testdeymer.presentation.theme.Gray40
import com.testdeymer.presentation.theme.NewsPulseTheme

@Composable
fun ListCompose(
    list: List<Any>,
    itemContent: @Composable (Any) -> Unit
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        items(list.size) { index ->
            itemContent(list[index])
            if(index < list.size - 1) {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(id = R.dimen.dimen_8),
                            bottom = dimensionResource(id = R.dimen.dimen_8)
                        ),
                    color = Gray40,
                    thickness = dimensionResource(id = R.dimen.dimen_1)
                )
            }
        }
        item { Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_12))) }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListComposePreview() {
    val exampleList = listOf(
        ItemUiModel(title = "Item 1", description = "Description 1", onClick = { println("Item 1") }),
        ItemUiModel(title = "Item 2", description = "Description 2", onClick = { println("Item 2") }),
        ItemUiModel(title = "Item 3", description = "Description 3", onClick = { println("Item 3") }),
    )
    NewsPulseTheme {
        ListCompose(
            list = exampleList,
            itemContent = { item ->
                ItemCompose(
                    itemUi = item as ItemUiModel,
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.dimen_8))
                )
            }
        )
    }
}
