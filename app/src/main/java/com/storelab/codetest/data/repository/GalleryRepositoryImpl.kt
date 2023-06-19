package com.storelab.codetest.data.repository

import com.storelab.codetest.data.database.FavoriteEntity
import com.storelab.codetest.data.database.LocalDataSource
import com.storelab.codetest.data.mapper.toModel
import com.storelab.codetest.data.network.PhotoResponse
import com.storelab.codetest.data.network.RemoteDataSource
import com.storelab.codetest.model.Photo
import com.storelab.codetest.model.setFavorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of GalleryRepository that will interact with the data source
 */
class GalleryRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : GalleryRepository {

    /**
     * Combine flows from remote api & local database
     * Return list of photos along with isFavorite flag set for any photo that was saved as favorite
     */
    override fun getPhotos(): Flow<List<Photo>> = flow {
        combine(loadPhotos(), getFavorites()) { photos, favorites ->
            photos.map { photo ->
                photo.setFavorite(favorites.contains(photo.id))
            }
        }.collect { photos ->
            emit(photos)
        }
    }

    private fun loadPhotos(): Flow<List<Photo>> {
        return remoteDataSource.getPhotos().map { it.map(PhotoResponse::toModel) }
    }

    private fun getFavorites(): Flow<List<String>> {
        return localDataSource.getFavorites().map { it.map(FavoriteEntity::id) }
    }

}
