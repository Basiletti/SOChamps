package com.basiletti.gino.sochamps.domain.usecases

import com.basiletti.gino.sochamps.domain.model.User
import com.basiletti.gino.sochamps.domain.repository.StackOverflowUsersRepository
import com.basiletti.gino.sochamps.util.Resource
import java.lang.Exception
import javax.inject.Inject

class GetSOUsersUseCase @Inject constructor(
    private val repository: StackOverflowUsersRepository
) {
    suspend operator fun invoke(): Resource<List<User>> {
        val response = try {
            repository.getTopUsers()
            //TODO: Mapper here for pairing followed contacts with roomDB?
        } catch (e: Exception) {
            return Resource.Error(message = e.toString())
        }
        return Resource.Success(response)
    }
}