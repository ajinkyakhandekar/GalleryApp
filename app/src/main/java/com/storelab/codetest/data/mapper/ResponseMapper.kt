package com.storelab.codetest.data.mapper

import com.storelab.codetest.data.network.PhotoResponse
import com.storelab.codetest.model.Photo

/**
 * Mapping functions to map data between Api Response & Ui Models
 */

fun PhotoResponse.toModel(): Photo {
    return Photo(
        id = id,
        image_url = download_url,
        isFavorite = false
    )
}
