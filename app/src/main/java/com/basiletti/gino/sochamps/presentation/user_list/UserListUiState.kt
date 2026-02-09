package com.basiletti.gino.sochamps.presentation.user_list

import com.basiletti.gino.sochamps.domain.model.User

data class UserListUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val users: List<User> = emptyList(),
    val showUnfollowDialog: Boolean = false,
    val focusedUser: User? = null,
)
