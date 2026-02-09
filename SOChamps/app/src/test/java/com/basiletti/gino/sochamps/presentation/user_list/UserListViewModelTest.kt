@file:OptIn(ExperimentalCoroutinesApi::class)

package com.basiletti.gino.sochamps.presentation.user_list

import app.cash.turbine.test
import com.basiletti.gino.sochamps.domain.usecases.GetSOUsersUseCase
import com.basiletti.gino.sochamps.domain.usecases.UpdateFollowUseCase
import com.basiletti.gino.sochamps.util.Resource
import com.basiletti.gino.sochamps.util.generateUserPresentationModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class UserListViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var sut:  UserListViewModel

    private val mockGetSOUsersUseCase = mock<GetSOUsersUseCase>()
    private val mockUpdateFollowUseCase = mock<UpdateFollowUseCase>()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        sut = UserListViewModel(
            getSOUsersUseCase = mockGetSOUsersUseCase,
            updateFollowUseCase = mockUpdateFollowUseCase
        )
    }

    @Test
    fun userListViewModel_whenInitialisedThenUiStateAsExpected() {
        runTest(testDispatcher) {

            sut.uiState.test {
                val initialState = awaitItem()
                assertEquals(0, initialState.users.size)
                assertEquals(false, initialState.isLoading)
                assertEquals(null, initialState.errorMessage)
            }
        }
    }

    @Test
    fun userListViewModelLoadUsers_whenSuccessfulThenUiStateAsExpected() {
        runTest(testDispatcher) {
            whenever(mockGetSOUsersUseCase.invoke()).thenReturn(Resource.Success(listOf(
                generateUserPresentationModel(displayName = "Alfie"),
                generateUserPresentationModel(displayName = "Ben"),
                generateUserPresentationModel(displayName = "Charlie"),
            )))

            sut.uiState.test {
                val initialState = awaitItem()
                assertEquals(0, initialState.users.size)
                assertEquals(false, initialState.isLoading)
                assertEquals(null, initialState.errorMessage)

                sut.loadUsers()
                val secondState = awaitItem()
                assertEquals(0, secondState.users.size)
                assertEquals(true, secondState.isLoading)
                assertEquals(null, secondState.errorMessage)

                val finalSate = awaitItem()
                assertEquals(false, finalSate.isLoading)
                assertEquals(3, finalSate.users.size)
                assertEquals(null, finalSate.errorMessage)
            }
        }
    }

    @Test
    fun userListViewModelLoadUsers_whenErrorThenUiStateAsExpected() {
        runTest(testDispatcher) {
            whenever(mockGetSOUsersUseCase.invoke()).thenReturn(Resource.Error(message = "Something went wrong"))

            sut.uiState.test {
                val initialState = awaitItem()
                assertEquals(0, initialState.users.size)
                assertEquals(false, initialState.isLoading)
                assertEquals(null, initialState.errorMessage)

                sut.loadUsers()
                val secondState = awaitItem()
                assertEquals(0, secondState.users.size)
                assertEquals(true, secondState.isLoading)
                assertEquals(null, secondState.errorMessage)

                val finalSate = awaitItem()
                assertEquals(false, finalSate.isLoading)
                assertEquals(0, finalSate.users.size)
                assertEquals("Something went wrong", finalSate.errorMessage)
            }
        }
    }

    @Test
    fun userListViewModel_whenFollowClickedThenUiStateAsExpected() {
        val userToBeFollowed = generateUserPresentationModel(displayName = "Ben")

        runTest(testDispatcher) {
            whenever(mockGetSOUsersUseCase.invoke()).thenReturn(Resource.Success(listOf(
                generateUserPresentationModel(displayName = "Alfie"),
                userToBeFollowed, //This is the user we'll be following
                generateUserPresentationModel(displayName = "Charlie"),
            )))
            whenever(mockUpdateFollowUseCase.invoke(userToBeFollowed)).thenReturn(
                generateUserPresentationModel(displayName = "Ben", isFollowing = true)
            )

            sut.uiState.test {
                val initialState = awaitItem()
                assertEquals(0, initialState.users.size)
                assertEquals(false, initialState.isLoading)
                assertEquals(false, initialState.showUnfollowDialog)
                assertEquals(null, initialState.focusedUser)

                sut.loadUsers()
                val secondState = awaitItem()
                assertEquals(0, secondState.users.size)
                assertEquals(true, secondState.isLoading)
                assertEquals(false, secondState.showUnfollowDialog)
                assertEquals(null, secondState.focusedUser)

                val loadedState = awaitItem()
                assertEquals(false, loadedState.isLoading)
                assertEquals(3, loadedState.users.size)
                assertEquals(false, loadedState.showUnfollowDialog)
                assertEquals(null, loadedState.focusedUser)
                assertEquals(null, loadedState.users[1].isFollowing)

                sut.onFollowButtonClicked(user = userToBeFollowed)
                val finalState = awaitItem()
                assertEquals(3, finalState.users.size)
                assertEquals(false, finalState.showUnfollowDialog)
                assertEquals(null, finalState.focusedUser)
                assertEquals(true, finalState.users[1].isFollowing)
            }
        }
    }

    @Test
    fun userListViewModel_whenUnfollowClickedThenUiStateAsExpected() {
        val followedUser = generateUserPresentationModel(displayName = "Ben", isFollowing = true)

        runTest(testDispatcher) {
            whenever(mockGetSOUsersUseCase.invoke()).thenReturn(Resource.Success(listOf(
                generateUserPresentationModel(displayName = "Alfie"),
                followedUser, //This is the user we'll be unfollowing
                generateUserPresentationModel(displayName = "Charlie"),
            )))

            sut.uiState.test {
                val initialState = awaitItem()
                assertEquals(0, initialState.users.size)
                assertEquals(false, initialState.isLoading)
                assertEquals(false, initialState.showUnfollowDialog)
                assertEquals(null, initialState.focusedUser)

                sut.loadUsers()
                val secondState = awaitItem()
                assertEquals(0, secondState.users.size)
                assertEquals(true, secondState.isLoading)
                assertEquals(false, secondState.showUnfollowDialog)
                assertEquals(null, secondState.focusedUser)

                val loadedState = awaitItem()
                assertEquals(false, loadedState.isLoading)
                assertEquals(3, loadedState.users.size)
                assertEquals(false, loadedState.showUnfollowDialog)
                assertEquals(null, loadedState.focusedUser)

                sut.onFollowButtonClicked(user = followedUser)
                val finalState = awaitItem()
                assertEquals(3, finalState.users.size)
                assertEquals(true, finalState.showUnfollowDialog)
                assertEquals(followedUser, finalState.focusedUser)
            }
        }
    }

}