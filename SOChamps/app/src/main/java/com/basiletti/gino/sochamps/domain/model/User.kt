package com.basiletti.gino.sochamps.domain.model

data class User(
    val id: Int,
    val name: String,
    val profileImageURL: String,
    val reputation: Int,
    val bronzeBadges: Int,
    val silverBadges: Int,
    val goldBadges: Int,
    var isFollowing: Boolean? = null,
)
