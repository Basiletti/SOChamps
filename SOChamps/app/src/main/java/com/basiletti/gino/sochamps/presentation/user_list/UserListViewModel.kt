package com.basiletti.gino.sochamps.presentation.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(

): ViewModel() {

    private val _uiState = MutableStateFlow(UserListUiState())
    val uiState: StateFlow<UserListUiState> = _uiState

    fun loadUsers() {
        if (!uiState.value.isLoading) {
            viewModelScope.launch {
               // _uiState.value = _uiState.value.copy(isLoading = true)


            }
        }
    }

}