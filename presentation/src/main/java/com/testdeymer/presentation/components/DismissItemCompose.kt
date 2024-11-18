package com.testdeymer.presentation.components

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.testdeymer.presentation.R
import com.testdeymer.presentation.models.ItemUiModel
import com.testdeymer.presentation.theme.IndianRed
import com.testdeymer.presentation.theme.NewsPulseTheme
import kotlinx.coroutines.launch

@Composable
fun DismissItemCompose(
    modifier: Modifier = Modifier,
    onItemDismissed: () -> Unit,
    itemContent: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val swipeState = rememberSwipeToDismissBoxState(
        confirmValueChange = { state ->
            if (state == SwipeToDismissBoxValue.EndToStart) {
                coroutineScope.launch {
                    onItemDismissed()
                }
                true
            } else {
                false
            }
        }
    )
    SwipeToDismissBox(
        modifier = modifier.animateContentSize(),
        state = swipeState,
        backgroundContent = {
            AnimatedVisibility(
                visible = swipeState.targetValue == SwipeToDismissBoxValue.EndToStart,
                enter = fadeIn()
            ) {
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(IndianRed)
                        .padding(horizontal = dimensionResource(id = R.dimen.dimen_8))
                ) {
                    TagCompose(
                        icon = Icons.Default.Delete,
                        text = stringResource(id = R.string.delete),
                        onClick = {},
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_4))
                    )
                }
            }
        },
        content = { itemContent() }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DismissItemPreview() {
    val items = remember {
        mutableStateListOf(
            ItemUiModel("1", "Item 1", "Description 1"),
            ItemUiModel("2", "Item 2", "Description 2"),
            ItemUiModel("3", "Item 3", "Description 3")
        )
    }
    NewsPulseTheme {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8))
        ) {
            items(items.size) { index ->
                DismissItemCompose(
                    onItemDismissed = { items.remove(items[index]) },
                    itemContent = {
                        ItemCompose(items[index])
                    }
                )
                DividerCompose(isVisible = (index < items.size - 1))
            }
        }
    }
}