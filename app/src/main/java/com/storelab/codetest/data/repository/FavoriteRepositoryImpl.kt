package com.storelab.codetest.data.repository

import com.storelab.codetest.data.database.FavoriteEntity
import com.storelab.codetest.data.database.LocalDataSource
import com.storelab.codetest.data.mapper.toFavoriteEntity
import com.storelab.codetest.data.mapper.toModel
import com.storelab.codetest.model.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of FavoriteRepository that will interact with the data source
 */
class FavoriteRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : FavoriteRepository {

    override suspend fun toggleFavorite(photo: Photo) {
        if (!photo.isFavorite) {
            localDataSource.addFavorite(photo.toFavoriteEntity())
        } else {
            localDataSource.removeFavorite(photo.id)
        }
    }

    override fun getFavorites(): Flow<List<Photo>> {
        return localDataSource.getFavorites().map { it.map(FavoriteEntity::toModel) }
    }
}
