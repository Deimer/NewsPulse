package com.testdeymer.newspulse.features.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.testdeymer.presentation.R
import com.testdeymer.newspulse.common.ErrorDetailCompose
import com.testdeymer.newspulse.common.LoadingScreenCompose
import com.testdeymer.presentation.components.DividerCompose
import com.testdeymer.presentation.components.LinkTextCompose
import com.testdeymer.presentation.components.TopBarCompose
import com.testdeymer.presentation.models.ItemUiModel
import com.testdeymer.presentation.theme.NewsPulseTheme

@Composable
fun DetailScreenCompose(
    viewModel: DetailScreenViewModel = hiltViewModel(),
    attributes: DetailScreenAttributes
) {
    val uiState by viewModel.detailUiState.collectAsState()
    val itemDetail by viewModel.itemUiState.collectAsState()
    viewModel.getDetail(attributes.objectId)
    when(uiState) {
        is DetailUiState.Success -> BodyCompose(attributes.actions, itemDetail)
        is DetailUiState.Loading -> LoadingScreenCompose()
        is DetailUiState.Error -> {
            val errorMessage = (uiState as DetailUiState.Error).message
            ErrorDetailCompose(errorMessage, attributes.snackbarHostState)
        }
    }
}

@Composable
private fun BodyCompose(
    actions: DetailScreenActions,
    itemDetail: ItemUiModel,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TopBarCompose(
            modifier = Modifier,
            subtitle = itemDetail.title,
            navigationIcon = R.drawable.ic_back,
            onNavigationClick = actions.onPrimaryAction
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    start = dimensionResource(id = R.dimen.dimen_12)
                )
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                modifier = Modifier.padding(
                    top = 16.dp,
                ),
                text = stringResource(id = R.string.author),
                style = MaterialTheme.typography.labelSmall,
            )
            Text(
                modifier = Modifier.padding(
                    top = 12.dp,
                    start = 8.dp,
                ),
                text = itemDetail.author,
                style = MaterialTheme.typography.headlineSmall,
            )
            DividerCompose()
            Text(
                modifier = Modifier.padding(
                    top = 16.dp,
                ),
                text = stringResource(id = R.string.last_updated),
                style = MaterialTheme.typography.labelSmall,
            )
            Text(
                modifier = Modifier.padding(
                    top = 12.dp,
                    start = 8.dp,
                ),
                text = itemDetail.fullDate,
                style = MaterialTheme.typography.headlineSmall,
            )
            DividerCompose()
            Text(
                modifier = Modifier.padding(
                    top = 16.dp,
                ),
                text = stringResource(id = R.string.comment_text),
                style = MaterialTheme.typography.labelSmall,
            )
            Text(
                modifier = Modifier.padding(
                    top = 12.dp,
                    start = 8.dp,
                ),
                text = itemDetail.commentText,
                style = MaterialTheme.typography.labelLarge,
            )
            DividerCompose()
            if (itemDetail.hasUrl) {
                Text(
                    modifier = Modifier.padding(
                        top = 16.dp,
                    ),
                    text = stringResource(id = R.string.more_details),
                    style = MaterialTheme.typography.labelSmall,
                )
                LinkTextCompose(
                    modifier = Modifier.padding(
                        top = 12.dp,
                        start = 8.dp,
                    ),
                    linkText = itemDetail.url,
                    onClick = { actions.onSecondaryAction(itemDetail.url) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BodyComposePreview() {
    val actions = DetailScreenActions(
        onPrimaryAction = {},
        onSecondaryAction = {}
    )
    val item = ItemUiModel(
        id = "1",
        title = "Sample Title",
        author = "John Doe",
        description = "Sample description for the item.",
        fullDate = "2024-11-12",
        commentText = "This is a sample comment text to illustrate the detail screen.",
        url = "https://example.com/details"
    )
    NewsPulseTheme {
        BodyCompose(
            actions = actions,
            itemDetail = item
        )
    }
}