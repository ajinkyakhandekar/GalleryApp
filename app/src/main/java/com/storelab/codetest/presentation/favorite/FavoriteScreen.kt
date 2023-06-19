package com.storelab.codetest.presentation.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.storelab.codetest.R
import com.storelab.codetest.model.Photo
import com.storelab.codetest.presentation.image.ImageList

/**
 * UI that will display list of favorite photos
 */

@Composable
fun FavoriteRoute(
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    //Collect values from this StateFlow and represents its latest value via State in a lifecycle-aware manner.
    val favoriteState by favoriteViewModel.favoriteState.collectAsStateWithLifecycle()

    FavoriteScreen(
        modifier = Modifier
            .padding(12.dp)
            .testTag("favorite_screen_tag"),
        favoriteState = favoriteState,
        onFavoriteClicked = favoriteViewModel::toggleFavorite
    )
}

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    favoriteState: FavoriteState,
    onFavoriteClicked: (photo: Photo) -> Unit
) {

    Box(modifier = modifier) {
        // Display UI corresponding to various states
        when (favoriteState) {
            FavoriteState.Loading -> {
                Text(text = stringResource(id = R.string.loading))
            }

            is FavoriteState.Success -> {
                if(favoriteState.favorites.isNotEmpty()){
                    ImageList(
                        photos = favoriteState.favorites,
                        onFavoriteClicked = onFavoriteClicked
                    )
                }else{
                    Text(text = stringResource(id = R.string.empty_favorites_message))
                }
            }

            is FavoriteState.Error -> {
                Text(text = "Error - ${favoriteState.message}")
            }
        }
    }
}
