package com.storelab.codetest.data.repository

import com.storelab.codetest.model.Photo
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {

    fun getPhotos(): Flow<List<Photo>>
}
