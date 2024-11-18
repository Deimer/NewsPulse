package com.testdeymer.newspulse.features.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.testdeymer.newspulse.common.ConnectionErrorScreenCompose
import com.testdeymer.newspulse.common.ErrorDetailCompose
import com.testdeymer.newspulse.common.LoadingScreenCompose
import com.testdeymer.presentation.R
import com.testdeymer.presentation.components.DismissItemCompose
import com.testdeymer.presentation.components.DividerCompose
import com.testdeymer.presentation.components.ItemCompose
import com.testdeymer.presentation.components.RefreshColumnCompose
import com.testdeymer.presentation.components.TopBarCompose
import com.testdeymer.presentation.models.ItemUiModel
import com.testdeymer.presentation.theme.NewsPulseTheme

@Composable
fun HomeScreenCompose(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    attributes: HomeScreenAttributes
) {
    val uiState by viewModel.homeUiState.collectAsState()
    val itemList by viewModel.itemList.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    when(uiState) {
        is HomeUiState.Success -> BodyCompose(
            attributes.actions,
            itemList,
            isRefreshing,
            viewModel::getHits,
            viewModel::deleteHitById
        )
        is HomeUiState.Loading -> LoadingScreenCompose()
        is HomeUiState.ConnectionError -> ConnectionErrorScreenCompose()
        is HomeUiState.Error -> {
            val errorMessage = (uiState as HomeUiState.Error).message
            ErrorDetailCompose(errorMessage, attributes.snackbarHostState)
        }
    }
}

@Composable
private fun BodyCompose(
    actions: HomeScreenActions,
    itemList: List<ItemUiModel>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    deleteItem: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBarCompose(
            title = stringResource(id = R.string.home),
            modifier = Modifier,
        )
        RefreshColumnCompose(
            isRefreshing = isRefreshing,
            onRefresh = onRefresh
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.dimen_12)
                    ),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8))
            ) {
                itemsIndexed(itemList) { index, item ->
                    DismissItemCompose(
                        onItemDismissed = {
                            deleteItem(item.id)
                        },
                        itemContent = {
                            ItemCompose(
                                itemUi = item,
                                onClick = { actions.onPrimaryAction(item.id) }
                            )
                        }
                    )
                    DividerCompose(isVisible = (index < itemList.size - 1))
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BodyComposePreview() {
    val exampleItems = MutableList(5) { index ->
        ItemUiModel(
            id = index.toString(),
            title = "Item $index",
            description = "Description of item $index",
            onClick = {}
        )
    }
    val actions = HomeScreenActions(onPrimaryAction = {})
    NewsPulseTheme {
        BodyCompose(
            actions = actions,
            itemList = exampleItems,
            isRefreshing = false,
            onRefresh = {}
        )
    }
}