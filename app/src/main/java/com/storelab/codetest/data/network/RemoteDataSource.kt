package com.storelab.codetest.data.network

import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
){
    fun getPhotos() = flow {
        emit(apiService.getPhotos())
    }
}
