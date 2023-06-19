package com.storelab.codetest.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.storelab.codetest.data.database.LocalDataSource
import com.storelab.codetest.data.fake.givenFavorites
import com.storelab.codetest.data.fake.givenPhotosResponse
import com.storelab.codetest.data.network.RemoteDataSource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GalleryRepositoryImplTest {

    private lateinit var sut: GalleryRepository
    private var localDataSource = mock<LocalDataSource>()
    private var remoteDataSource = mock<RemoteDataSource>()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        sut = GalleryRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `given photos and favorites when fetched then list of combined data obtained`() = runTest {
        whenever(localDataSource.getFavorites())
            .thenReturn(flowOf(givenFavorites))

        whenever(remoteDataSource.getPhotos())
            .thenReturn(flowOf(givenPhotosResponse))

        val photos = sut.getPhotos()

        photos.test {
            assertThat(awaitItem().size).isEqualTo(3)
            awaitComplete()
        }
    }

    @Test
    fun `given photos and favorites when fetched then check if item is favorite`() = runTest {
        whenever(localDataSource.getFavorites())
            .thenReturn(flowOf(givenFavorites))

        whenever(remoteDataSource.getPhotos())
            .thenReturn(flowOf(givenPhotosResponse))

        val photos = sut.getPhotos()

        photos.test {
            assertThat(awaitItem()[0].isFavorite).isTrue()
            awaitComplete()
        }
    }

    @Test
    fun `given photos and favorites when fetched then check if item is Not favorite`() = runTest {
        whenever(localDataSource.getFavorites())
            .thenReturn(flowOf(givenFavorites))

        whenever(remoteDataSource.getPhotos())
            .thenReturn(flowOf(givenPhotosResponse))

        val photos = sut.getPhotos()

        photos.test {
            assertThat(awaitItem()[2].isFavorite).isFalse()
            awaitComplete()
        }
    }
}
