package com.storelab.codetest.data.database

import android.content.Context
import androidx.room.Room
import com.storelab.codetest.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt Module to provide Local Database dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provide instance of [GalleryDatabase]
     */
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        GalleryDatabase::class.java,
        Constant.GALLERY_DATABASE
    ).build()


    /**
     * Provide instance of [GalleryDao]
     */
    @Singleton
    @Provides
    fun provideDao(
        db: GalleryDatabase
    ): GalleryDao {
        return db.galleryDao()
    }
}
