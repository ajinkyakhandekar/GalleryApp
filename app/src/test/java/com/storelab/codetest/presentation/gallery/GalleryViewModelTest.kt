package com.storelab.codetest.presentation.gallery

import com.google.common.truth.Truth.assertThat
import com.storelab.codetest.data.fake.givenPhotos
import com.storelab.codetest.data.repository.FavoriteRepository
import com.storelab.codetest.data.repository.GalleryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GalleryViewModelTest {

    private val galleryRepository = mock<GalleryRepository>()
    private val favoriteRepository = mock<FavoriteRepository>()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewModel initialised then assert state as Loading`() = runTest {
        val sut = GalleryViewModel(galleryRepository, favoriteRepository)

        assertThat(sut.galleryState.value).isEqualTo(GalleryState.Loading)
    }

    @Test
    fun `given galleryState initialised when galleryState collected then assert state as Loading`() = runTest {
        val sut = GalleryViewModel(galleryRepository, favoriteRepository)

        val job = launch {
            sut.galleryState.collect()
        }
        assertThat(sut.galleryState.value).isEqualTo(GalleryState.Loading)
        job.cancel()
    }

    @Test
    fun `given photo list when galleryState collected then assert state as Success`() = runTest {
        whenever(galleryRepository.getPhotos()).thenReturn(flowOf(givenPhotos))
        val sut = GalleryViewModel(galleryRepository, favoriteRepository)

        val job = launch {
            sut.galleryState.collect()
        }
        advanceUntilIdle()

        assertThat(sut.galleryState.value).isEqualTo(GalleryState.Success(givenPhotos))
        job.cancel()
    }

    @Test
    fun `given exception when galleryState collected then assert state as Error`() = runTest {
        val errorMessage = "An error occurred"

        whenever(galleryRepository.getPhotos())
            .thenReturn(flow { throw Exception(errorMessage) })

        val sut = GalleryViewModel(galleryRepository, favoriteRepository)

        val job = launch {
            sut.galleryState.collect()
        }
        advanceUntilIdle()

        assertThat(sut.galleryState.value).isEqualTo(GalleryState.Error(errorMessage))
        job.cancel()
    }
}
