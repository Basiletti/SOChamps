package com.basiletti.gino.sochamps.presentation.user_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.basiletti.gino.sochamps.domain.model.User
import com.basiletti.gino.sochamps.presentation.components.SOErrorBanner

@Composable
fun UserListScreen(
    modifier: Modifier = Modifier,
    viewModel: UserListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.loadUsers()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        uiState.errorMessage?.let { error ->
            SOErrorBanner(errorText = error)
        }

        if (uiState.users.isEmpty()) {
            EmptyState()

        } else {
            UserList(users = uiState.users)
        }
    }
}

@Composable
fun UserList(
    modifier: Modifier = Modifier,
    users: List<User>
) {

}

@Composable
fun EmptyState(modifier: Modifier = Modifier) {
    Text("No users to show!")
}
