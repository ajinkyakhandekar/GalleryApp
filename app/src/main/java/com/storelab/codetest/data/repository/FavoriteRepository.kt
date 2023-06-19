package com.storelab.codetest.data.repository

import com.storelab.codetest.model.Photo
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun toggleFavorite(photo : Photo)

    fun getFavorites(): Flow<List<Photo>>
}
