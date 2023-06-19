package com.storelab.codetest.presentation.gallery

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

@Composable
fun GalleryRoute(
    galleryViewModel: GalleryViewModel = hiltViewModel(),
) {
    //Collect values from this StateFlow and represents its latest value via State in a lifecycle-aware manner.
    val galleryState by galleryViewModel.galleryState.collectAsStateWithLifecycle()

    GalleryScreen(
        modifier = Modifier
            .padding(12.dp)
            .testTag("gallery_screen_tag"),
        galleryState = galleryState,
        onFavoriteClicked = galleryViewModel::toggleFavorite
    )
}

@Composable
fun GalleryScreen(
    modifier: Modifier = Modifier,
    galleryState: GalleryState,
    onFavoriteClicked: (photo: Photo) -> Unit
) {
    Box(modifier = modifier) {

        // Display UI corresponding to various states
        when (galleryState) {
            GalleryState.Loading -> {
                Text(text = stringResource(id = R.string.loading))
            }

            is GalleryState.Success -> {
                if (galleryState.photos.isNotEmpty()) {
                    ImageList(
                        photos = galleryState.photos,
                        onFavoriteClicked = onFavoriteClicked
                    )
                } else {
                    Text(text = stringResource(id = R.string.empty_gallery_message))
                }
            }

            is GalleryState.Error -> {
                Text(text = "Error - ${galleryState.message}")
            }
        }
    }
}
