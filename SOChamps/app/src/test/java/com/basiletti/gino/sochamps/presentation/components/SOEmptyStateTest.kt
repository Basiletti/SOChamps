package com.basiletti.gino.sochamps.presentation.components

import androidx.compose.ui.test.assertIsDisplayed
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Test
import com.basiletti.gino.sochamps.R
import junit.framework.TestCase.assertEquals

@RunWith(RobolectricTestRunner::class)
class SOEmptyStateTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSOEmptyState_titleIsDisplayed() {
        composeTestRule.setContent {
            SOEmptyState(
                text = "Empty State Text",
            )
        }

        composeTestRule.onNodeWithText("Empty State Text").assertIsDisplayed()
        composeTestRule.onNodeWithTag("emptyStateIcon").assertDoesNotExist()
    }

    @Test
    fun testSOEmptyState_titleButtonAndIconAreDisplayed() {
        composeTestRule.setContent {
            SOEmptyState(
                text = "Empty State Text",
                iconRes = R.drawable.ic_user,
                buttonText = "Click Me",
            )
        }

        composeTestRule.onNodeWithText("Empty State Text").assertIsDisplayed()
        composeTestRule.onNodeWithTag("emptyStateIcon").assertIsDisplayed()
        composeTestRule.onNodeWithText("Click Me").assertIsDisplayed()
    }

    @Test
    fun testSOEmptyState_buttonClickDetected() {
        var iconClicked = false

        composeTestRule.setContent {
            SOEmptyState(
                text = "Empty State Text",
                iconRes = R.drawable.ic_user,
                buttonText = "Click Me",
                onButtonClicked = {
                    iconClicked = true
                }
            )
        }

        assertEquals(false, iconClicked)
        composeTestRule.onNodeWithText("Click Me").assertIsDisplayed()
        composeTestRule.onNodeWithText("Click Me").performClick()
        assertEquals(true, iconClicked)
    }


}