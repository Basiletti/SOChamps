package com.basiletti.gino.sochamps.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FollowingDao {
    @Query("SELECT * FROM `following`")
    fun getAll(): List<FollowingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(following: FollowingEntity)

    @Query("DELETE FROM `following` WHERE userId = :userId")
    fun deleteFollowingByUserId(userId: Int)

}