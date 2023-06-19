package com.storelab.codetest.presentation.gallery

import com.storelab.codetest.model.Photo

sealed class GalleryState {
    object Loading : GalleryState()
    data class Success(val photos: List<Photo>) : GalleryState()
    data class Error(val message: String = "") : GalleryState()
}
