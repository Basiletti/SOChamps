package com.basiletti.gino.sochamps.presentation.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basiletti.gino.sochamps.domain.model.User
import com.basiletti.gino.sochamps.domain.usecases.GetSOUsersUseCase
import com.basiletti.gino.sochamps.domain.usecases.UpdateFollowUseCase
import com.basiletti.gino.sochamps.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getSOUsersUseCase: GetSOUsersUseCase,
    private val updateFollowUseCase: UpdateFollowUseCase,
): ViewModel() {
    private val _uiState = MutableStateFlow(UserListUiState())
    val uiState: StateFlow<UserListUiState> = _uiState

    fun loadUsers() {
        if (!uiState.value.isLoading) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = true,
                        errorMessage = null,
                    )
                    delay(1000) //Artificial network delay just to demonstrate loading state on UI.

                    when (val result = getSOUsersUseCase.invoke()) {
                        is Resource.Success -> {
                            _uiState.value = _uiState.value.copy(
                                users = result.data!!,
                                isLoading = false,
                                errorMessage = null,
                            )
                        }

                        is Resource.Error -> {
                            _uiState.value = _uiState.value.copy(
                                users = listOf(),
                                isLoading = false,
                                errorMessage = result.message,
                            )
                        }
                    }
                }
            }
        }
    }

    fun hideDialog() {
        _uiState.value = _uiState.value.copy(
            showUnfollowDialog = false,
            focusedUser = null,
        )
    }

    fun onUnfollowConfirmed() {
        _uiState.value.focusedUser?.let { user ->
            toggleUserFollow(user)
        }

        _uiState.value = _uiState.value.copy(
            showUnfollowDialog = false,
            focusedUser = null
        )
    }

    fun onFollowButtonClicked(user: User) {
        if (user.isFollowing == true) {
            _uiState.value = _uiState.value.copy(
                showUnfollowDialog = true,
                focusedUser = user,
            )

        } else {
            toggleUserFollow(user)
        }
    }

    fun toggleUserFollow(user: User) {
        viewModelScope.launch {
            val updatedUser = withContext(Dispatchers.IO) {
                updateFollowUseCase.invoke(user)
            }

            _uiState.value =_uiState.value.copy(
                users = _uiState.value.users.map { existingUser ->
                    if (existingUser.id == updatedUser.id) {
                        updatedUser
                    } else {
                        existingUser
                    }
                }
            )
        }
    }

}