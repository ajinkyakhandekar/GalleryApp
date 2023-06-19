package com.storelab.codetest.data.network

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.nio.charset.StandardCharsets

/**
 * Test RemoteDataSource using MockWebServer to simulate remote web server and Turbine to test flows
 */
class RemoteDataSourceTest {

    private lateinit var sut: RemoteDataSource
    private lateinit var apiService: ApiService
    private lateinit var mockWebServer: MockWebServer
    private val apiResponseFileName = "photosApiResponse.json"

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiService = RetrofitHelper.testApiInstance(mockWebServer.url(".").toString())
        sut = RemoteDataSource(apiService)

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `test Api result for successful response`() = runTest {

        mockWebServer.enqueueResponse(apiResponseFileName, HttpURLConnection.HTTP_OK)

        sut.getPhotos().test {
            assertThat(awaitItem().size).isEqualTo(100)
            awaitComplete()
        }
    }

    @Test
    fun `test Api result for error response`() = runTest {

        mockWebServer.enqueueResponse(apiResponseFileName, HttpURLConnection.HTTP_NOT_FOUND)

        sut.getPhotos().test {
            awaitError()
        }
    }
}


internal fun MockWebServer.enqueueResponse(fileName: String, code: Int) {

    // Load json response from file in java resources directory
    val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)

    val source = inputStream?.let { inputStream.source().buffer() }
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(code)
                .setBody(source.readString(StandardCharsets.UTF_8))
        )
    }
}


object RetrofitHelper {

    // Provide Retrofit api instance for MockWebServer
    fun testApiInstance(baseUrl: String): ApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
