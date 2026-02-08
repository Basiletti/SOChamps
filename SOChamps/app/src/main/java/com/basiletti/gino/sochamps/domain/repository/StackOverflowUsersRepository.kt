package com.basiletti.gino.sochamps.domain.repository

import com.basiletti.gino.sochamps.data.local.FollowingEntity
import com.basiletti.gino.sochamps.domain.model.User

interface StackOverflowUsersRepository {
    suspend fun getTopUsers(): List<User>
    suspend fun getFollowedUsers(): List<FollowingEntity>
    suspend fun followUser(user: User)
    suspend fun deleteFollow(userId: Int)
}