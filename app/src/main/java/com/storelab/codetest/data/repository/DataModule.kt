package com.storelab.codetest.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Hilt Module to provide Repository interface implementation
 */
@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindGalleryRepository(
        galleryRepository: GalleryRepositoryImpl,
    ): GalleryRepository

    @Binds
    fun bindFavoriteRepository(
        favoriteRepository: FavoriteRepositoryImpl,
    ): FavoriteRepository

}
