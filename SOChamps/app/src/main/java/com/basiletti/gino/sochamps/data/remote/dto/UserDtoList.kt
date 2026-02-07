package com.basiletti.gino.sochamps.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserDtoList(
    @SerializedName("items")
    val items: List<UserDto>,
    @SerializedName("has_more")
    val hasMore: Boolean,
    @SerializedName("quota_max")
    val quotaMax: Int,
    @SerializedName("quota_remaining")
    val quotaRemaining: Int
)