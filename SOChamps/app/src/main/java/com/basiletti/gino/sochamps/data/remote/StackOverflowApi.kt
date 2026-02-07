package com.basiletti.gino.sochamps.data.remote

import com.basiletti.gino.sochamps.data.remote.dto.UserDto
import retrofit2.http.GET

interface StackOverflowApi {

    @GET("/2.2/users?page=1&pagesize=20&order=desc&sort=reputation&site=stackoverflow")
    suspend fun getTopUsers(): List<UserDto>

}