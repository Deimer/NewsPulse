package com.testdeymer.newspulse.common

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.testdeymer.presentation.R
import com.testdeymer.presentation.components.LottieCompose
import com.testdeymer.presentation.theme.NewsPulseTheme
import com.testdeymer.presentation.utils.PresentationConstants.AnimationConstants.ITERATION_FOREVER

@Composable
fun LoadingScreenCompose() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieCompose(
            rawRes = R.raw.loading,
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dimen_20)),
            size = dimensionResource(id = R.dimen.dimen_100),
            iterations = ITERATION_FOREVER
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
private fun LoadingScreenComposePreview() {
    NewsPulseTheme {
        Scaffold {
            LoadingScreenCompose()
        }
    }
}