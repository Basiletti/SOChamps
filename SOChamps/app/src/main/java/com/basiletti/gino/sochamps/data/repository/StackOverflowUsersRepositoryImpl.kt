package com.basiletti.gino.sochamps.data.repository

import com.basiletti.gino.sochamps.data.local.FollowingDao
import com.basiletti.gino.sochamps.data.local.FollowingEntity
import com.basiletti.gino.sochamps.data.remote.StackOverflowApi
import com.basiletti.gino.sochamps.domain.mapper.UserMapper
import com.basiletti.gino.sochamps.domain.model.User
import com.basiletti.gino.sochamps.domain.repository.StackOverflowUsersRepository
import javax.inject.Inject

class StackOverflowUsersRepositoryImpl @Inject constructor(
    private val api: StackOverflowApi,
    private val mapper: UserMapper,
    private val dao: FollowingDao,
): StackOverflowUsersRepository {

    override suspend fun getTopUsers(): List<User> {
        return mapper.convertDtoToPresentation(api.getTopUsers().items)
    }

    override suspend fun getFollowedUsers(): List<FollowingEntity> {
        return dao.getAll()
    }

    override suspend fun followUser(user: User) {
        dao.insert(FollowingEntity(0, user.name, user.id))
    }

    override suspend fun deleteFollow(userId: Int) {
        dao.deleteFollowingByUserId(userId)
    }

}