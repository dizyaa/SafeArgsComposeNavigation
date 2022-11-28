package dev.dizyaa.safe_args_navigation_compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
public inline fun <reified T: SafeArgs> NavHost(
    navController: NavHostController,
    startCommand: SafeNavCommand<T>,
    modifier: Modifier = Modifier,
    route: String? = null,
    noinline builder: NavGraphBuilder.() -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startCommand.destination,
        modifier = modifier,
        route = route,
        builder = builder,
    )
}