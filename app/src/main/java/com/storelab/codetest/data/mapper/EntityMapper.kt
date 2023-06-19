package com.storelab.codetest.data.mapper

import com.storelab.codetest.data.database.FavoriteEntity
import com.storelab.codetest.model.Photo

/**
 * Mapping functions to map data between Database Entities & Ui Models
 */

fun FavoriteEntity.toModel(): Photo {
    return Photo(
        id = id,
        image_url = image_url,
        isFavorite = true
    )
}

fun Photo.toFavoriteEntity(): FavoriteEntity {
    return FavoriteEntity(
        id = id,
        image_url = image_url
    )
}
