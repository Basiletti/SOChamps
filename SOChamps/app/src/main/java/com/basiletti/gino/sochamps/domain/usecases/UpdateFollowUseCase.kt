package com.basiletti.gino.sochamps.domain.usecases

import com.basiletti.gino.sochamps.domain.model.User
import com.basiletti.gino.sochamps.domain.repository.StackOverflowUsersRepository
import javax.inject.Inject

class UpdateFollowUseCase @Inject constructor(
    val repository: StackOverflowUsersRepository
) {
    suspend operator fun invoke(
        user: User
    ): User {
        return if (user.isFollowing) {
            repository.deleteFollow(user.id)
            user.copy(isFollowing = false)

        } else {
            repository.followUser(user)
            user.copy(isFollowing = true)
        }
    }
}