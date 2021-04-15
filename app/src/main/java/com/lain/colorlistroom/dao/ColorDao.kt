package com.lain.colorlistroom.dao

import androidx.room.*
import com.lain.colorlistroom.entity.Color

@Dao
interface ColorDao {

    @Query("SELECT * FROM colors")
    suspend fun findAll() : List<Color>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(color: Color)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(color: Color): Int

    @Delete
    suspend fun delete(color: Color)

}