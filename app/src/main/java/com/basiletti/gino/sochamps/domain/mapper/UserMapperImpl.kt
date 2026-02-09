package com.basiletti.gino.sochamps.domain.mapper

import com.basiletti.gino.sochamps.data.remote.dto.UserDto
import com.basiletti.gino.sochamps.domain.model.User

class UserMapperImpl: UserMapper {

    override fun convertDtoToPresentation(userDtoList: List<UserDto>): List<User> {
        return userDtoList.map { userDto ->
            User(
                id = userDto.user_id,
                name = userDto.display_name,
                profileImageURL = userDto.profile_image,
                reputation = userDto.reputation,
                bronzeBadges = userDto.badge_counts.bronze,
                silverBadges = userDto.badge_counts.silver,
                goldBadges = userDto.badge_counts.gold,
            )
        }
    }

}