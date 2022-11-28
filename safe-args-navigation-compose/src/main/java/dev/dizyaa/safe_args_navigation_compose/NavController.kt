package dev.dizyaa.safe_args_navigation_compose

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator
import androidx.navigation.navOptions
import kotlinx.parcelize.Parcelize
import java.net.URLEncoder

inline fun <reified T: SafeArgs> NavController.navigate(
    command: SafeNavCommand<T>,
    args: T,
    noinline builder: NavOptionsBuilder.() -> Unit
) {
    navigate(command, args, navOptions(builder))
}

inline fun <reified T: SafeArgs> NavController.navigate(
    command: SafeNavCommand<T>,
    args: T,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    val json = SafeArgsNavigation.serializer.toJson(args)
    val path = URLEncoder.encode(json, "UTF-8")
    val route = command.destination + "?$SAFE_ARGS_KEY=${path}"

    navigate(
        route = route,
        navOptions = navOptions,
        navigatorExtras = navigatorExtras
    )
}