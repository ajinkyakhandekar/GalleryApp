package com.storelab.codetest.presentation.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.storelab.codetest.R

sealed class Screens(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector,
    val tag: String
) {
    object Gallery : Screens(
        route = "gallery",
        title = R.string.gallery,
        icon = Icons.Filled.Home,
        tag = "galleryTab",
    )

    object Favorite : Screens(
        route = "favorite",
        title = R.string.favorite,
        icon = Icons.Filled.Favorite,
        tag = "favoriteTab",
    )
}

val screens = listOf(
    Screens.Gallery,
    Screens.Favorite,
)