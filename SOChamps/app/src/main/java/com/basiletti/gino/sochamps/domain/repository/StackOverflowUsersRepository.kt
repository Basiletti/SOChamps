package com.basiletti.gino.sochamps.domain.repository

import com.basiletti.gino.sochamps.domain.model.User

interface StackOverflowUsersRepository {
    suspend fun getTopUsers(): List<User>
}