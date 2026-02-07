@file:OptIn(ExperimentalMaterial3Api::class)

package com.basiletti.gino.sochamps.presentation.user_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.basiletti.gino.sochamps.R
import com.basiletti.gino.sochamps.domain.model.User
import com.basiletti.gino.sochamps.presentation.components.SOEmptyState
import com.basiletti.gino.sochamps.presentation.components.SOErrorBanner
import com.basiletti.gino.sochamps.presentation.components.SOHeader
import com.basiletti.gino.sochamps.presentation.components.SOSpinner

@Composable
fun UserListScreen(
    viewModel: UserListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.loadUsers()
    }

    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        SOHeader(
            headerText = stringResource(R.string.stack_overflow_champs),
            iconEndRes = R.drawable.ic_download
        )

        AnimatedVisibility(uiState.errorMessage != null) {
            uiState.errorMessage?.let { error ->
                SOErrorBanner(errorText = error)
            }
        }

        when {
            uiState.isLoading -> {
                SOSpinner(text = stringResource(R.string.champions_loading_text))
            }
            uiState.users.isEmpty() -> {
                SOEmptyState(
                    text = stringResource(R.string.no_users_found),
                    iconRes = R.drawable.ic_user,
                    buttonText = stringResource(R.string.try_again),
                    onButtonClicked = viewModel::loadUsers
                )
            }
            else -> {
                UserList(
                    users = uiState.users
                )
            }
        }

    }
}

@Composable
fun UserList(
    modifier: Modifier = Modifier,
    users: List<User>
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(users) { index, user ->
            UserInformation(user = user)
        }
    }
}

@Composable
fun UserInformation(
    modifier: Modifier = Modifier,
    user: User,
) {
    Text("Username: ${user.name}")
}
