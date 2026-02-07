package com.basiletti.gino.sochamps.data.remote.dto

data class UserDtoList(
    val items: List<UserDto>,
    val has_more: Boolean,
    val quota_max: Int,
    val quota_remaining: Int
)