package com.storelab.codetest.data.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.storelab.codetest.data.fake.givenFavorites
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class LocalDataSourceTest {

    private lateinit var sut: LocalDataSource
    private lateinit var galleryDatabase: GalleryDatabase
    private lateinit var galleryDao: GalleryDao

    @Before
    fun setup() {
        galleryDatabase = Room.inMemoryDatabaseBuilder(
            context = ApplicationProvider.getApplicationContext(),
            klass = GalleryDatabase::class.java
        ).allowMainThreadQueries().build()

        galleryDao = galleryDatabase.galleryDao()

        sut = LocalDataSource(galleryDao)
    }

    @After
    fun teardown() {
        galleryDatabase.close()
    }

    @Test
    fun test_insert_favorites() = runTest {
        sut.addFavorite(givenFavorites[0])
        sut.addFavorite(givenFavorites[1])

        sut.getFavorites().test {
            assertThat(awaitItem().size).isEqualTo(2)
        }
    }

    @Test
    fun test_insert_duplicate_favorite() = runTest {
        sut.addFavorite(givenFavorites[0])
        sut.addFavorite(givenFavorites[0])

        sut.getFavorites().test {
            assertThat(awaitItem().size).isEqualTo(1)
        }
    }

    @Test
    fun test_remove_favorite() = runTest {
        sut.addFavorite(givenFavorites[0])
        sut.addFavorite(givenFavorites[1])

        sut.removeFavorite(givenFavorites[1].id)

        sut.getFavorites().test {
            assertThat(awaitItem().size).isEqualTo(1)
        }
    }

    @Test
    fun test_remove_favorite_if_no_entries_inserted() = runTest {

        sut.removeFavorite(givenFavorites[1].id)

        sut.getFavorites().test {
            assertThat(awaitItem().size).isEqualTo(0)
        }
    }
}
