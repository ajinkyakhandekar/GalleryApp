package com.storelab.codetest.presentation.favorite

import com.storelab.codetest.model.Photo

sealed class FavoriteState {
    object Loading : FavoriteState()
    data class Success(val favorites: List<Photo>) : FavoriteState()
    data class Error(val message: String = "") : FavoriteState()
}
