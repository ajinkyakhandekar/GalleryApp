package com.storelab.codetest.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.storelab.codetest.data.database.LocalDataSource
import com.storelab.codetest.data.fake.givenFavorites
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
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
class FavoriteRepositoryImplTest {

    private lateinit var sut: FavoriteRepository
    private var localDataSource = mock<LocalDataSource>()

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
        sut = FavoriteRepositoryImpl(localDataSource)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given favorites when fetched then list of favorites obtained`() = runTest {
        whenever(localDataSource.getFavorites())
            .thenReturn(flowOf(givenFavorites))

        val favorites = sut.getFavorites()

        favorites.test {
            assertThat(awaitItem().size).isEqualTo(2)
            awaitComplete()
        }
    }
}
