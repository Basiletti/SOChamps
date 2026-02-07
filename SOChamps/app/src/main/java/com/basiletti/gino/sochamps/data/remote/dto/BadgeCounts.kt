package com.basiletti.gino.sochamps.data.remote.dto


import com.google.gson.annotations.SerializedName

data class BadgeCounts(
    @SerializedName("bronze")
    val bronze: Int,
    @SerializedName("gold")
    val gold: Int,
    @SerializedName("silver")
    val silver: Int
)