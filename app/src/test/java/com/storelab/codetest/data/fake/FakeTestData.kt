package com.storelab.codetest.data.fake

import com.storelab.codetest.data.database.FavoriteEntity
import com.storelab.codetest.data.network.PhotoResponse
import com.storelab.codetest.model.Photo


val givenFavorites = listOf(
    FavoriteEntity(
        id = "0",
        image_url = "https://picsum.photos/id/0/5000/3333"
    ),
    FavoriteEntity(
        id = "1",
        image_url = "https://picsum.photos/id/101/2621/1747"
    )
)

val givenPhotos = listOf(
    Photo(
        id = "0",
        image_url = "https://picsum.photos/id/0/5000/3333",
        isFavorite = false
    ),
    Photo(
        id = "1",
        image_url = "https://picsum.photos/id/101/2621/1747",
        isFavorite = true
    ),
    Photo(
        id = "2",
        image_url = "https://picsum.photos/id/99/4912/3264",
        isFavorite = true
    ),
    Photo(
        id = "3",
        image_url = "https://picsum.photos/id/99/4912/3264",
        isFavorite = false
    )
)

val givenPhotosResponse = listOf(
    PhotoResponse(
        id = "0",
        author = "Alejandro Escamilla",
        width = 5000,
        height = 3333,
        url = "https://unsplash.com/photos/yC-Yzbqy7PY",
        download_url = "https://picsum.photos/id/0/5000/3333"
    ),
    PhotoResponse(
        id = "1",
        author = "Christian Bardenhorst",
        width = 2621,
        height = 1747,
        url = "https://unsplash.com/photos/8lMhzUjD1Wk",
        download_url = "https://picsum.photos/id/101/2621/1747"
    ),
    PhotoResponse(
        id = "2",
        author = "Jon Toney",
        width = 4912,
        height = 3264,
        url = "https://unsplash.com/photos/xyDQNmT6vSs",
        download_url = "https://picsum.photos/id/99/4912/3264"
    )
)
