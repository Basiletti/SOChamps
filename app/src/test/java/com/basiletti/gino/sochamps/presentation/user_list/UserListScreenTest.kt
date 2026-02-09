package com.basiletti.gino.sochamps.presentation.user_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.basiletti.gino.sochamps.util.generateUserPresentationModel
import org.junit.Test
import kotlinx.coroutines.flow.MutableStateFlow
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@RunWith(RobolectricTestRunner::class)
class UserListScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var mockViewModel: UserListViewModel

    @Test
    fun userListScreen_mainContentDisplayed() {
        mockViewModel = mock {
            on { uiState } doReturn MutableStateFlow(UserListUiState())
        }

        composeTestRule.setContent {
            UserListScreen(
                viewModel = mockViewModel
            )
        }

        //We expect to see the header & empty state shown initially
        composeTestRule.onNodeWithTag("SOHeader").assertIsDisplayed()
        composeTestRule.onNodeWithTag("emptyState").assertIsDisplayed()

        //We definitely would not expect an error banner to be shown, nor a loading spinner or user list at this stage.
        composeTestRule.onNodeWithTag("errorBanner").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("loadingSpinner").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("userList").assertIsNotDisplayed()
    }

    @Test
    fun userListScreen_loadingStateDisplayed() {
        mockViewModel = mock {
            on { uiState } doReturn MutableStateFlow(UserListUiState(
                isLoading = true
            ))
        }

        composeTestRule.setContent {
            UserListScreen(
                viewModel = mockViewModel
            )
        }

        //We expect to see the header & loading state shown initially
        composeTestRule.onNodeWithTag("SOHeader").assertIsDisplayed()
        composeTestRule.onNodeWithTag("loadingSpinner").assertIsDisplayed()

        //We don't expect to see user list / error banner / empty state
        composeTestRule.onNodeWithTag("errorBanner").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("emptyState").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("userList").assertIsNotDisplayed()
    }

    @Test
    fun userListScreen_errorBannerDisplayed() {
        mockViewModel = mock {
            on { uiState } doReturn MutableStateFlow(UserListUiState(
                errorMessage = "Test Error"
            ))
        }

        composeTestRule.setContent {
            UserListScreen(
                viewModel = mockViewModel
            )
        }

        //We expect to see the header & loading state shown initially
        composeTestRule.onNodeWithTag("SOHeader").assertIsDisplayed()
        composeTestRule.onNodeWithTag("errorBanner").assertIsDisplayed()
    }

    @Test
    fun userListScreen_usersListDisplayed() {
        mockViewModel = mock {
            on { uiState } doReturn MutableStateFlow(UserListUiState(
                users = listOf(
                    generateUserPresentationModel("Mike"),
                    generateUserPresentationModel("Steve")
                )
            ))
        }

        composeTestRule.setContent {
            UserListScreen(
                viewModel = mockViewModel
            )
        }

        //We expect to see the user list and header. Mike and Steve should be displayed.
        composeTestRule.onNodeWithTag("SOHeader").assertIsDisplayed()
        composeTestRule.onNodeWithTag("userList").assertIsDisplayed()
        composeTestRule.onNodeWithText("Mike").assertIsDisplayed()
        composeTestRule.onNodeWithText("Steve").assertIsDisplayed()

        composeTestRule.onNodeWithTag("emptyState").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("errorBanner").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("loadingSpinner").assertIsNotDisplayed()
    }

    @Test
    fun userListScreen_dialogDisplayed() {
        mockViewModel = mock {
            on { uiState } doReturn MutableStateFlow(UserListUiState(
                users = listOf(
                    generateUserPresentationModel("Mike"),
                    generateUserPresentationModel("Steve")
                ),
                focusedUser = generateUserPresentationModel("Mike"),
                showUnfollowDialog = true
            ))
        }

        composeTestRule.setContent {
            UserListScreen(
                viewModel = mockViewModel
            )
        }

        //We expect to see the user list and header. Mike and Steve should be displayed.
        composeTestRule.onNodeWithTag("SOHeader").assertIsDisplayed()
        composeTestRule.onNodeWithTag("userList").assertIsDisplayed()
        composeTestRule.onNodeWithTag("unfollowDialog").assertIsDisplayed()
    }

}