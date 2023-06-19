package com.storelab.codetest.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.storelab.codetest.utils.Constant

/**
 * Room entity used to create a table with columns corresponding to the fields in the class
 */
@Entity(tableName = Constant.FAVORITE_TABLE)
data class FavoriteEntity(
    @PrimaryKey
    val id: String,
    val image_url: String
)
