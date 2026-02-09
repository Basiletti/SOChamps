package com.basiletti.gino.sochamps.data.remote

import com.basiletti.gino.sochamps.data.remote.dto.UserDtoList
import retrofit2.http.GET
import retrofit2.http.Query

interface StackOverflowApi {

    @GET("/2.2/users/")
    suspend fun getTopUsers(
        @Query("page") page: Int = 1,
        @Query("pagesize") pageSize: Int = 20,
        @Query("order") order: String = "desc",
        @Query("sort") sort: String = "reputation",
        @Query("site") site: String = "stackoverflow"
    ): UserDtoList

}