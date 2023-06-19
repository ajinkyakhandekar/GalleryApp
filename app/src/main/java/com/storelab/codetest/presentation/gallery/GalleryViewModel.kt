package com.storelab.codetest.presentation.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.storelab.codetest.data.repository.FavoriteRepository
import com.storelab.codetest.data.repository.GalleryRepository
import com.storelab.codetest.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    galleryRepository: GalleryRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    // Get list of Photos from repository and map to GalleryState
    // Transform flow<T> received from repository into StateFlow<T> using stateIn function
    // StateFlow<T> will emit the current and new state updates to its collectors
    val galleryState: StateFlow<GalleryState> =
        galleryRepository
            .getPhotos()
            .map<List<Photo>, GalleryState>(GalleryState::Success)
            .catch { error -> emit(GalleryState.Error(error.message.toString())) }
            .onStart { emit(GalleryState.Loading) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = GalleryState.Loading
            )

    // UI event to favorite or unfavorite any photo
    fun toggleFavorite(photo: Photo) {
        viewModelScope.launch {
            favoriteRepository.toggleFavorite(photo)
        }
    }
}
