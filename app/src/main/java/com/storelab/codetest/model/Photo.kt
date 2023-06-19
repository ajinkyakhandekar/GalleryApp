package com.storelab.codetest.model

/**
 * Model class to display UI
 */
data class Photo(
    val id: String,
    val image_url: String,
    val isFavorite: Boolean
)

fun Photo.setFavorite(isFavorite: Boolean): Photo {
    return copy(isFavorite = isFavorite)
}
