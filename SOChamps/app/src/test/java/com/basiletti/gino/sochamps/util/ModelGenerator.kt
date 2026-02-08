package com.basiletti.gino.sochamps.util

import com.basiletti.gino.sochamps.data.remote.dto.BadgeCountsDto
import com.basiletti.gino.sochamps.data.remote.dto.UserDto

fun generateUserDtoModel(
    displayName: String = "Jon Skeet",
): UserDto {
    return UserDto(
        user_id = 22656,
        display_name = displayName,
        profile_image = "https://www.gravatar.com/avatar/6d8ebb1bff6ba6e8c13833d3caeb7eda?s=256&d=identicon&r=PG",
        reputation = 131072,
        badge_counts = generateBadgeCountsDto(),
        accept_rate = 0,
        creation_date = 1222430705,
        last_access_date = 1770494971,
        reputation_change_year = 1569,
        reputation_change_quarter = 1569,
        reputation_change_month = 305,
        reputation_change_week = 305,
        reputation_change_day = 0,
        is_employee = false,
        last_modified_date = 1770371111,
        account_id = 0,
        user_type = "registered",
        location = "Reading, United Kingdom",
        website_url = "http://csharpindepth.com",
        link = "https://stackoverflow.com/users/"
    )
}

fun generateBadgeCountsDto(): BadgeCountsDto {
    return BadgeCountsDto(
        bronze = 100,
        silver = 80,
        gold = 50
    )
}