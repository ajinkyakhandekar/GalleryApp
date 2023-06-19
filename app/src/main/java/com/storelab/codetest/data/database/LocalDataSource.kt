package com.storelab.codetest.data.database

import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val galleryDao: GalleryDao
) {

    suspend fun addFavorite(favoriteEntity: FavoriteEntity) =
        galleryDao.addFavorite(favoriteEntity)

    suspend fun removeFavorite(id: String) =
        galleryDao.removeFavorite(id)

    fun getFavorites() = galleryDao.getFavorites()

}
