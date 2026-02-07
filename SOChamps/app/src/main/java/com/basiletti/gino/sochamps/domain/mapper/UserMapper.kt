package com.basiletti.gino.sochamps.domain.mapper

import com.basiletti.gino.sochamps.data.remote.dto.UserDto
import com.basiletti.gino.sochamps.domain.model.User

interface UserMapper {
    fun convertDtoToPresentation(userDto: UserDto): User
}