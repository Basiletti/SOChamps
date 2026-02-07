package com.basiletti.gino.sochamps.data.repository

import com.basiletti.gino.sochamps.data.remote.StackOverflowApi
import com.basiletti.gino.sochamps.domain.mapper.UserMapper
import com.basiletti.gino.sochamps.domain.model.User
import com.basiletti.gino.sochamps.domain.repository.StackOverflowUsersRepository
import javax.inject.Inject

class StackOverflowUsersRepositoryImpl @Inject constructor(
    private val api: StackOverflowApi,
    private val mapper: UserMapper,
): StackOverflowUsersRepository {

    override suspend fun getTopUsers(): List<User> {
        return mapper.convertDtoToPresentation(api.getTopUsers().items)
    }

}