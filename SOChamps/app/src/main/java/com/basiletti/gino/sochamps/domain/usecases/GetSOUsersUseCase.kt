package com.basiletti.gino.sochamps.domain.usecases

import com.basiletti.gino.sochamps.domain.model.User
import com.basiletti.gino.sochamps.domain.repository.StackOverflowUsersRepository
import com.basiletti.gino.sochamps.util.Resource
import java.lang.Exception
import javax.inject.Inject

class GetSOUsersUseCase @Inject constructor(
    private val repository: StackOverflowUsersRepository,
    private val getFollowingListUseCase: GetFollowingListUseCase,
) {
    suspend operator fun invoke(): Resource<List<User>> {
        val response = try {
            repository.getTopUsers()

        } catch (e: Exception) {
            return Resource.Error(message = e.toString())
        }

        val followedUsers = getFollowingListUseCase.invoke()
        for (user in response) {
            user.isFollowing = followedUsers.any { it.userId == user.id }
        }

        return Resource.Success(response)
    }
}