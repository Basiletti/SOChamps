@file:OptIn(ExperimentalMaterial3Api::class)

package com.basiletti.gino.sochamps.presentation.user_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.basiletti.gino.sochamps.R
import com.basiletti.gino.sochamps.domain.model.User
import com.basiletti.gino.sochamps.presentation.components.AsyncImageLoader
import com.basiletti.gino.sochamps.presentation.components.BadgeType
import com.basiletti.gino.sochamps.presentation.components.SOAlertDialog
import com.basiletti.gino.sochamps.presentation.components.SOBadge
import com.basiletti.gino.sochamps.presentation.components.SOEmptyState
import com.basiletti.gino.sochamps.presentation.components.SOErrorBanner
import com.basiletti.gino.sochamps.presentation.components.SOHeader
import com.basiletti.gino.sochamps.presentation.components.SOMiniButton
import com.basiletti.gino.sochamps.presentation.components.SOSpinner
import com.basiletti.gino.sochamps.presentation.components.SOSubtitle
import com.basiletti.gino.sochamps.presentation.components.SOTitle
import com.basiletti.gino.sochamps.ui.theme.darkGreen
import com.basiletti.gino.sochamps.ui.theme.spaceRegular
import com.basiletti.gino.sochamps.ui.theme.spaceSmall
import com.basiletti.gino.sochamps.ui.theme.spaceXSmall

@Composable
fun UserListScreen(
    viewModel: UserListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.loadUsers()
    }

    if (uiState.showUnfollowDialog) {
        SOAlertDialog(
            onConfirmation = viewModel::onUnfollowConfirmed,
            onDismissRequest = viewModel::hideDialog,
            dialogTitle = stringResource(R.string.unfollow_user),
            dialogText = stringResource(
                R.string.are_you_sure_you_wish_to_unfollow,
                uiState.focusedUser?.name ?: ""
            )
        )
    }

    UserListContent(
        viewModel = viewModel,
        uiState = uiState,
    )
}

@Composable
fun UserListContent(
    viewModel: UserListViewModel,
    uiState: UserListUiState,
) {
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        SOHeader(
            modifier = Modifier.testTag("SOHeader"),
            headerText = stringResource(R.string.stack_overflow_champs),
            iconEndRes = R.drawable.ic_download,
            onIconClicked = viewModel::loadUsers
        )

        AnimatedVisibility(uiState.errorMessage != null) {
            SOErrorBanner(
                modifier = Modifier.fillMaxWidth().testTag("errorBanner"),
                errorText = uiState.errorMessage ?: ""
            )
        }

        when {
            uiState.isLoading -> {
                SOSpinner(
                    modifier = Modifier.testTag("loadingSpinner"),
                    text = stringResource(R.string.champions_loading_text)
                )
            }
            uiState.users.isEmpty() -> {
                SOEmptyState(
                    modifier = Modifier.testTag("emptyState"),
                    text = stringResource(R.string.no_users_found),
                    iconRes = R.drawable.ic_user,
                    buttonText = stringResource(R.string.try_again),
                    onButtonClicked = viewModel::loadUsers
                )
            }
            else -> {
                UserList(
                    users = uiState.users,
                    onFollowClicked = viewModel::onFollowClicked
                )
            }
        }

    }
}

@Composable
fun UserList(
    users: List<User>,
    onFollowClicked: (User) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxSize().testTag("userList")) {
        itemsIndexed(users) { index, user ->
            Column {
                UserInformation(
                    user = user,
                    onFollowClicked = onFollowClicked
                )
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun UserInformation(
    user: User,
    onFollowClicked: (User) -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(
                horizontal = spaceRegular,
                vertical = spaceSmall
            )
            .fillMaxWidth(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spaceSmall)
        ) {
            AsyncImageLoader(imageUrl = user.profileImageURL)

            val buttonTextRes = if (user.isFollowing == true) R.string.following else R.string.follow
            val buttonColor = if (user.isFollowing == true) darkGreen else Color.DarkGray
            SOMiniButton(
                text = stringResource(buttonTextRes),
                backgroundColor = buttonColor,
                onButtonClicked = {
                    onFollowClicked(user)
                }
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(spaceXSmall)
        ) {
            SOTitle(
                modifier = Modifier,
                text = user.name
            )
            SOSubtitle(
                modifier = Modifier,
                text = "Reputation: ${user.reputation}"
            )

            Row {
                SOBadge(text = user.bronzeBadges.toString(), badgeType = BadgeType.BRONZE)
                SOBadge(text = user.silverBadges.toString(), badgeType = BadgeType.SILVER)
                SOBadge(text = user.goldBadges.toString(), badgeType = BadgeType.GOLD)
            }
        }
    }

}
