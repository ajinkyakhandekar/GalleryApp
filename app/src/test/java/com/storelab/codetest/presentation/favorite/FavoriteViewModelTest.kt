package com.storelab.codetest.presentation.favorite

import com.google.common.truth.Truth.assertThat
import com.storelab.codetest.data.fake.givenPhotos
import com.storelab.codetest.data.repository.FavoriteRepository
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
class FavoriteViewModelTest {

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
        val sut = FavoriteViewModel(favoriteRepository)

        assertThat(sut.favoriteState.value).isEqualTo(FavoriteState.Loading)
    }

    @Test
    fun `given favoriteState initialised when favoriteState collected then assert state as Loading`() = runTest {
        val sut = FavoriteViewModel(favoriteRepository)

        val job = launch {
            sut.favoriteState.collect()
        }
        assertThat(sut.favoriteState.value).isEqualTo(FavoriteState.Loading)
        job.cancel()
    }

    @Test
    fun `given favorite list when favoriteState collected then assert state as Success`() = runTest {
        whenever(favoriteRepository.getFavorites()).thenReturn(flowOf(givenPhotos))
        val sut = FavoriteViewModel(favoriteRepository)

        val job = launch {
            sut.favoriteState.collect()
        }
        advanceUntilIdle()

        assertThat(sut.favoriteState.value).isEqualTo(FavoriteState.Success(givenPhotos))
        job.cancel()
    }

    @Test
    fun `given exception when favoriteState collected then assert state as Error`() = runTest {
        val errorMessage = "An error occurred"

        whenever(favoriteRepository.getFavorites())
            .thenReturn(flow { throw Exception(errorMessage) })

        val sut = FavoriteViewModel(favoriteRepository)

        val job = launch {
            sut.favoriteState.collect()
        }
        advanceUntilIdle()

        assertThat(sut.favoriteState.value).isEqualTo(FavoriteState.Error(errorMessage))
        job.cancel()
    }
}
