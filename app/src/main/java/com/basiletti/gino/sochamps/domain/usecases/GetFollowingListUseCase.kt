package com.basiletti.gino.sochamps.domain.usecases

import com.basiletti.gino.sochamps.data.local.FollowingEntity
import com.basiletti.gino.sochamps.domain.repository.StackOverflowUsersRepository
import javax.inject.Inject

class GetFollowingListUseCase @Inject constructor(
    val repository: StackOverflowUsersRepository
) {
    suspend operator fun invoke(): List<FollowingEntity> {
        return repository.getFollowedUsers()
    }
}