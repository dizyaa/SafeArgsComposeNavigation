package dev.dizyaa.safe_args_navigation_compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.get
import androidx.navigation.navArgument

public inline fun <reified A: SafeArgs, B: SafeArgs> NavGraphBuilder.navigation(
    startDestination: SafeNavCommand<A>,
    route: SafeNavCommand<B>,
    builder: NavGraphBuilder.() -> Unit
): Unit = destination(
    navDestination = NavGraphBuilder(
        provider = provider,
        startDestination = startDestination.destination,
        route = route.destination,
    ).apply(builder)
)

public inline fun <reified T: SafeArgs> NavGraphBuilder.composable(
    command: SafeNavCommand<T>,
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline content: @Composable (NavBackStackEntry) -> Unit
) {
    addDestination(
        ComposeNavigator.Destination(provider[ComposeNavigator::class], content).apply {
            val argument = navArgument(SAFE_ARGS_KEY) { type = NavType.StringType }
            addArgument(argument.name, argument.argument)

            command.destination + "?$SAFE_ARGS_KEY={$SAFE_ARGS_KEY}"

            deepLinks.forEach { deepLink ->
                addDeepLink(deepLink)
            }
        }
    )
}

public const val SAFE_ARGS_KEY = "args"