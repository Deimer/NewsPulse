package com.testdeymer.newspulse.navigation

import com.testdeymer.newspulse.navigation.Routes.DETAIL
import com.testdeymer.newspulse.navigation.Routes.HOME
import com.testdeymer.newspulse.navigation.Routes.SPLASH
import com.testdeymer.newspulse.navigation.Routes.WEB

object Routes {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val DETAIL = "detail"
    const val WEB = "web"
}

object RouteArguments {
    const val OBJECT_ID = "objectId"
    const val URL = "url"
}

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens(SPLASH)
    object HomeScreen: AppScreens(HOME)
    object DetailScreen: AppScreens(DETAIL)
    object WebScreen: AppScreens(WEB)
}