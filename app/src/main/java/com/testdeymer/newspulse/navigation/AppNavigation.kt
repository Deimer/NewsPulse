package com.testdeymer.newspulse.navigation

import android.net.Uri
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.testdeymer.newspulse.features.detail.DetailScreenActions
import com.testdeymer.newspulse.features.detail.DetailScreenAttributes
import com.testdeymer.newspulse.features.detail.DetailScreenCompose
import com.testdeymer.newspulse.features.home.HomeScreenActions
import com.testdeymer.newspulse.features.home.HomeScreenAttributes
import com.testdeymer.newspulse.features.home.HomeScreenCompose
import com.testdeymer.newspulse.features.splash.SplashScreenActions
import com.testdeymer.newspulse.features.splash.SplashScreenCompose
import com.testdeymer.newspulse.features.web.WebScreenActions
import com.testdeymer.newspulse.features.web.WebScreenAttributes
import com.testdeymer.newspulse.features.web.WebScreenCompose
import com.testdeymer.presentation.components.SnackBarCompose
import com.testdeymer.newspulse.navigation.AppScreens.SplashScreen
import com.testdeymer.newspulse.navigation.AppScreens.HomeScreen
import com.testdeymer.newspulse.navigation.AppScreens.DetailScreen
import com.testdeymer.newspulse.navigation.AppScreens.WebScreen
import com.testdeymer.newspulse.navigation.RouteArguments.OBJECT_ID
import com.testdeymer.newspulse.navigation.RouteArguments.URL
import com.testdeymer.presentation.utils.PresentationConstants.AnimationConstants.TRANSITION_DURATION
import com.testdeymer.presentation.utils.toNegative

@Composable
fun AppNavigation(
    snackbarHostState: SnackbarHostState
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackBarCompose(snackbarHostState) },
        content = { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.background
            ) {
                BodyCompose(
                    navController = navController,
                    snackbarHostState = snackbarHostState,
                )
            }
        }
    )
}

@Composable
private fun BodyCompose(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    NavHost(
        navController = navController,
        startDestination = SplashScreen.route,
    ) {
        composable(
            route = SplashScreen.route,
            enterTransition = { inFadeAnimation() },
            exitTransition = { outFadeAnimation() }
        ) {
            SplashScreenCompose(actions = SplashScreenActions(
                onPrimaryAction = {
                    navController.popBackStack()
                    navController.navigate(route = HomeScreen.route)
                },
            ))
        }
        composable(
            route = HomeScreen.route,
            enterTransition = { inFadeAnimation() },
            exitTransition = { outFadeAnimation() }
        ) {
            HomeScreenCompose(attributes = HomeScreenAttributes(
                snackbarHostState = snackbarHostState,
                actions = HomeScreenActions(
                    onPrimaryAction = { objectId ->
                        navController.navigate(route = "${DetailScreen.route}/$objectId")
                    },
                ),
            ))
        }
        composable(
            route = "${DetailScreen.route}/{$OBJECT_ID}",
            enterTransition = { inFadeAnimation() },
            exitTransition = { outFadeAnimation() },
            arguments = listOf(
                navArgument(name = OBJECT_ID) {
                    type = StringType
                }
            )
        ) {
            DetailScreenCompose(
                attributes = DetailScreenAttributes(
                    objectId = it.arguments?.getString(OBJECT_ID).orEmpty(),
                    snackbarHostState = snackbarHostState,
                    actions = DetailScreenActions(
                        onPrimaryAction = {
                            navController.popBackStack()
                        },
                        onSecondaryAction = { url ->
                            val encodedUrl = Uri.encode(url)
                            navController.navigate(route = "${WebScreen.route}/$encodedUrl")
                        }
                    ),
                )
            )
        }
        composable(
            route = "${WebScreen.route}/{$URL}",
            enterTransition = { inFadeAnimation() },
            exitTransition = { outFadeAnimation() },
            arguments = listOf(
                navArgument(name = URL) {
                    type = StringType
                }
            )
        ) {
            val decodeUrl = it.arguments?.getString(URL)?.let { encodedUrl -> Uri.decode(encodedUrl) }.orEmpty()
            WebScreenCompose(
                attributes = WebScreenAttributes(
                    url = decodeUrl,
                    actions = WebScreenActions(
                        onPrimaryAction = {
                            navController.popBackStack()
                        }
                    ),
                )
            )
        }
    }
}

private fun inFadeAnimation(): EnterTransition {
    return slideInHorizontally(initialOffsetX = { TRANSITION_DURATION.toNegative() }) + fadeIn()
}

private fun outFadeAnimation(): ExitTransition {
    return slideOutHorizontally(targetOffsetX = { TRANSITION_DURATION }) + fadeOut()
}