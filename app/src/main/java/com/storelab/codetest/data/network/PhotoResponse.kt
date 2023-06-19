package com.storelab.codetest.data.network

/**
 * Model class that contain the data from the JSON response
 */
data class PhotoResponse(
    val author: String,
    val download_url: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)
