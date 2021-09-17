package com.mikepenz.storyblok.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.mikepenz.storyblok.BuildConfig
import com.mikepenz.storyblok.sdk.model.Story
import org.osmdroid.config.Configuration

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // needed for osmandroid
        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID

        setContent {
            MainLayout()
        }
    }
}

sealed class Screen(val title: String) {
    object StoryList : Screen("StoryList")
    object StoryDetails : Screen("StoryDetails")
}

data class BottomNavigationitem(
    val route: String,
    val icon: ImageVector,
    val iconContentDescription: String
)

val bottomNavigationItems = listOf(
    BottomNavigationitem(
        Screen.StoryList.title,
        Icons.Default.Info,
        "Story"
    )
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainLayout() {
    val navController = rememberAnimatedNavController()

    StoryblokTheme {
        ProvideWindowInsets {
            Scaffold(
                bottomBar = {
                    BottomNavigation {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentRoute = navBackStackEntry?.destination?.route

                        bottomNavigationItems.forEach { bottomNavigationitem ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        bottomNavigationitem.icon,
                                        contentDescription = bottomNavigationitem.iconContentDescription
                                    )
                                },
                                selected = currentRoute == bottomNavigationitem.route,
                                onClick = {
                                    navController.navigate(bottomNavigationitem.route) {
                                        popUpTo(navController.graph.id)
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }
                    }
                },
                modifier = Modifier
                    .background(MaterialTheme.colors.primarySurface)
                    .navigationBarsPadding()
            ) { paddingValues ->

                AnimatedNavHost(navController, startDestination = Screen.StoryList.title) {
                    composable(
                        route = Screen.StoryList.title,
                        exitTransition = { _, _ ->
                            slideOutHorizontally() +
                                    fadeOut(animationSpec = tween(1000))
                        },
                        popEnterTransition = { _, _ ->
                            slideInHorizontally()
                        }
                    ) {
                        StoryListScreen(
                            paddingValues = paddingValues,
                            storySelected = {
                                navController.navigate(Screen.StoryDetails.title + "/${it.name}")
                            }
                        )
                    }
                    composable(
                        route = Screen.StoryDetails.title + "/{story}",
                        enterTransition = { _, _ ->
                            slideInHorizontally() +
                                    fadeIn(animationSpec = tween(1000))
                        },
                        popExitTransition = { _, _ ->
                            slideOutHorizontally()
                        }
                    ) { backStackEntry ->
                        StoryDetailsScreen(
                            backStackEntry.arguments?.get("story") as String,
                            popBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview(@PreviewParameter(StoryProvider::class) story: Story) {
    MaterialTheme {
        StoryView(story, storySelected = {})
    }
}
