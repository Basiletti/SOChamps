package com.basiletti.gino.sochamps.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FollowingEntity::class], version = 1)
abstract class SODatabase : RoomDatabase() {
    abstract fun followingDao(): FollowingDao
}