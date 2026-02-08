package com.basiletti.gino.sochamps.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "following")
data class FollowingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val userId: Int,
)