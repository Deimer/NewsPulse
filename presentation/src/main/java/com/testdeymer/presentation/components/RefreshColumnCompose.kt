package com.testdeymer.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.testdeymer.presentation.R
import com.testdeymer.presentation.models.ItemUiModel
import com.testdeymer.presentation.theme.Gray40
import com.testdeymer.presentation.theme.NewsPulseTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefreshColumnCompose(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit
) {
    val pullToRefreshState = rememberPullToRefreshState()
    PullToRefreshBox(
        modifier = modifier.fillMaxSize(),
        isRefreshing = isRefreshing,
        state = pullToRefreshState,
        onRefresh = onRefresh
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun RefreshColumnComposePreview() {
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val itemContent: @Composable () -> Unit = {
        val list = listOf(
            ItemUiModel(title = "Item 1", description = "Description 1", onClick = { println("Item 1") }),
            ItemUiModel(title = "Item 2", description = "Description 2", onClick = { println("Item 2") }),
            ItemUiModel(title = "Item 3", description = "Description 3", onClick = { println("Item 3") }),
        )
        LazyColumn {
            items(list.size) { index ->
                ItemCompose(
                    itemUi = list[index],
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.dimen_8))
                )
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
        }
    }
    NewsPulseTheme {
        RefreshColumnCompose(
            isRefreshing = isRefreshing,
            onRefresh = {
                coroutineScope.launch {
                    isRefreshing = true
                    delay(2000)
                    isRefreshing = false
                }
            },
            content = itemContent
        )
    }
}