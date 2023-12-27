package md.labs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import coil.Coil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import md.labs.model.MyImageLoaderFactory
import md.labs.model.petfinderdata.Pet
import md.labs.ui.Navigation
import md.labs.ui.screens.detail.DetailScreen
import md.labs.ui.screens.list.ListScreen
import md.labs.ui.screens.login.LoginScreen
import md.labs.ui.screens.splash.SplashScreen
import java.net.URLDecoder

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Coil.setImageLoader(MyImageLoaderFactory(this).newImageLoader())

        setContent {
            //ScreenContent(Navigation.Splash.route)
            ScreenContent(Navigation.List.route)
        }
    }
}

@Composable
fun ScreenContent(startDestination: String) {
    val navController = rememberNavController()
    val transitionMillis = 300

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition    = defaultEnterTransition(transitionMillis),
        exitTransition     = defaultExitTransition(transitionMillis),
        popEnterTransition = defaultPopEnterTransition(transitionMillis),
        popExitTransition  = defaultPopExitTransition(transitionMillis)
    ) {
        composable(
            route = Navigation.Splash.route
        ) {
            SplashScreen(navController = navController)
        }
        composable(route = Navigation.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Navigation.List.route) {
            ListScreen(navController = navController)
        }
        composable(route = Navigation.Detail.route + "/{id}") { backStackEntry ->
            val id: String? = backStackEntry.arguments?.getString("id")
            DetailScreen(navController = navController, id?.toInt())
        }
    }
}

@Composable
@Suppress("SameParameterValue")
private fun defaultPopExitTransition(transitionMillis: Int):
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
    {
        fadeOut(
            animationSpec = tween(transitionMillis)
        ) + slideOutOfContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.End,
            animationSpec = tween(transitionMillis)
        )
    }

@Composable
@Suppress("SameParameterValue")
private fun defaultPopEnterTransition(transitionMillis: Int):
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
    {
        fadeIn(
            animationSpec = tween(transitionMillis)
        ) + slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.End,
            animationSpec = tween(transitionMillis)
        )
    }

@Composable
@Suppress("SameParameterValue")
private fun defaultExitTransition(transitionMillis: Int):
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
    {
        fadeOut(
            animationSpec = tween(transitionMillis)
        ) + slideOutOfContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Start,
            animationSpec = tween(transitionMillis)
        )
    }

@Composable
@Suppress("SameParameterValue")
private fun defaultEnterTransition(transitionMillis: Int):
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
    {
        fadeIn(
            animationSpec = tween(transitionMillis)
        ) + slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Start,
            animationSpec = tween(transitionMillis)
        )
    }

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    ScreenContent(Navigation.Login.route)
}