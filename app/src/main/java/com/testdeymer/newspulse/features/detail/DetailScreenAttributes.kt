package com.testdeymer.newspulse.features.detail

import androidx.compose.material3.SnackbarHostState

data class DetailScreenAttributes(
    val objectId: String,
    val actions: DetailScreenActions,
    val snackbarHostState: SnackbarHostState
)
