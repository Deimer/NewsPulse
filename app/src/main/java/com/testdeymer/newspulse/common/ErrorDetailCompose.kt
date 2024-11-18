package com.testdeymer.newspulse.common

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.testdeymer.presentation.R
import kotlinx.coroutines.launch

@Composable
fun ErrorDetailCompose(
    errorMessage: String? = null,
    snackbarHostState: SnackbarHostState
) {
    val snackbarScope = rememberCoroutineScope()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        snackbarScope.launch {
            val message = errorMessage ?: context.getString(R.string.error_generic)
            snackbarHostState.showSnackbar(message = message)
        }
    }
}