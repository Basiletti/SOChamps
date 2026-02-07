package com.basiletti.gino.sochamps.presentation.user_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basiletti.gino.sochamps.domain.usecases.GetSOUsersUseCase
import com.basiletti.gino.sochamps.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getSOUsersUseCase: GetSOUsersUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(UserListUiState())
    val uiState: StateFlow<UserListUiState> = _uiState

    fun loadUsers() {
        if (!uiState.value.isLoading) {
            viewModelScope.launch {
                with(Dispatchers.IO) {
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

}