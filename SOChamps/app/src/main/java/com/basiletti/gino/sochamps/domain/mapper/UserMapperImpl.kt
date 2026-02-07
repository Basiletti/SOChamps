package com.basiletti.gino.sochamps.domain.mapper

import com.basiletti.gino.sochamps.data.remote.dto.UserDto
import com.basiletti.gino.sochamps.domain.model.User

class UserMapperImpl: UserMapper {
    override fun convertDtoToPresentation(userDto: UserDto): User {
        return User(
            id = userDto.user_id,
            name = userDto.display_name,
            profileImageURL = userDto.profile_image,
            reputation = userDto.reputation
        )
    }
}