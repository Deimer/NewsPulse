package com.testdeymer.newspulse.features.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.testdeymer.presentation.R
import com.testdeymer.presentation.components.LottieCompose
import com.testdeymer.presentation.theme.NewsPulseTheme
import com.testdeymer.presentation.utils.PresentationConstants.AnimationConstants.ITERATION_SIMPLE

@Composable
fun SplashScreenCompose(
    actions: SplashScreenActions
) {
    BodyContent(actions)
}

@Composable
private fun BodyContent(
    actions: SplashScreenActions
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieCompose(
            rawRes = R.raw.logo,
            size = dimensionResource(id = R.dimen.dimen_120),
            iterations = ITERATION_SIMPLE,
            onAnimationEnd = { actions.onPrimaryAction.invoke() }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = dimensionResource(id = R.dimen.dimen_100)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(R.string.title_splash),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.dimen_8))
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    NewsPulseTheme {
        Scaffold {
            SplashScreenCompose(actions = SplashScreenActions(
                onPrimaryAction = {},
            ))
        }
    }
}