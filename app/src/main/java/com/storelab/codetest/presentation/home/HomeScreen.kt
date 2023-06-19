package com.storelab.codetest.presentation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.storelab.codetest.presentation.favorite.FavoriteRoute
import com.storelab.codetest.presentation.gallery.GalleryRoute

/**
 * Base composable that holds Bottom navigation bar and its corresponding screens,
 * along with providing [HomeState] which contains NavHostController that can be used to
 * handle the navigation between various composables
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeState: HomeState = rememberHomeState(),
) {
    Scaffold(
        content = {
            BottomNavHost(
                navController = homeState.navController,
                modifier = Modifier.padding(it)
            )
        },
        bottomBar = {
            BottomNavBar(
                currentDestination = homeState.currentDestination,
                navigateToDestination = homeState::navigateToDestination
            )
        }
    )
}

/**
 * Container for the navigation destination.
 * NavHost - provides the builder used to construct the graph
 */
@Composable
fun BottomNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = Screens.Gallery.route
    ) {
        composable(
            route = Screens.Gallery.route
        ) {
            GalleryRoute()
        }

        composable(
            route = Screens.Favorite.route
        ) {
            FavoriteRoute()
        }
    }
}
