package com.storelab.codetest.presentation.image

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.storelab.codetest.R
import com.storelab.codetest.model.Photo

@Composable
fun ImageList(
    modifier: Modifier = Modifier,
    photos: List<Photo>,
    onFavoriteClicked: (photo: Photo) -> Unit = {}
) {
    // Get grid state that will survive recomposition and restore the scroll position on recomposition
    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        state = gridState
    ) {
        items(photos.size) { index ->
            val photo = photos[index]
            ImageItem(
                modifier = Modifier.padding(6.dp),
                url = photo.image_url,
                isFavorite = photo.isFavorite,
                onFavoriteClicked = { onFavoriteClicked(photo) }
            )
        }
    }
}

@Composable
fun ImageItem(
    modifier: Modifier = Modifier,
    url: String,
    isFavorite: Boolean,
    onFavoriteClicked: () -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
    ) {

        Box {
            AsyncImage(
                model = url,
                contentDescription = stringResource(id = R.string.photos)
            )

            val tint = if (!isFavorite) Color.White
            else Color.Red

            Icon(
                modifier = Modifier
                    .size(36.dp)
                    .clickable { onFavoriteClicked() }
                    .padding(4.dp)
                    .align(Alignment.TopEnd),
                imageVector = Icons.Filled.Favorite,
                tint = tint,
                contentDescription = stringResource(id = R.string.favotite_icon)
            )
        }
    }
}
