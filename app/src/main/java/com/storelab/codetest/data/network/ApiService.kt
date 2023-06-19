package com.storelab.codetest.data.network

import com.storelab.codetest.utils.Constant
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface to define Api endpoints
 */
interface ApiService {

    @GET("v2/list")
    suspend fun getPhotos(
        @Query("limit") limit: String = Constant.PHOTO_LOAD_MAX_LIMIT,
    ): List<PhotoResponse>
}
