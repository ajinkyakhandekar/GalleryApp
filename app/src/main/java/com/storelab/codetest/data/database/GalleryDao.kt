package com.storelab.codetest.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [FavoriteEntity] access
 */
@Dao
interface GalleryDao {

    /**
     * Insert [FavoriteEntity] to database
     * Replace entity if it already exist
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favoriteEntity: FavoriteEntity)

    /**
     * Delete [FavoriteEntity] from database
     * @param id Id of the entity to be deleted
     */
    @Query("DELETE FROM favorite_table WHERE id = :id")
    suspend fun removeFavorite(id: String)

    /**
     * Return stream of [FavoriteEntity] from the database
     */
    @Query("SELECT * FROM favorite_table")
    fun getFavorites(): Flow<List<FavoriteEntity>>
}
