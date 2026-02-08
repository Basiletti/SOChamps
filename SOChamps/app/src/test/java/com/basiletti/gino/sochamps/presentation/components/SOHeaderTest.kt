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
class SOHeaderTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSOHeader_titleIsDisplayed() {
        composeTestRule.setContent {
            SOHeader(
                headerText = "Test Header",
            )
        }

        composeTestRule.onNodeWithText("Test Header").assertExists()
        composeTestRule.onNodeWithText("Test Header").assertIsDisplayed()
        composeTestRule.onNodeWithTag("iconEnd").assertDoesNotExist()
    }

    @Test
    fun testSOHeader_iconIsDisplayed() {
        composeTestRule.setContent {
            SOHeader(
                headerText = "Test Header",
                iconEndRes = R.drawable.ic_download,
            )
        }

        composeTestRule.onNodeWithText("Test Header").assertExists()
        composeTestRule.onNodeWithText("Test Header").assertIsDisplayed()

        composeTestRule.onNodeWithTag("iconEnd").assertExists()
        composeTestRule.onNodeWithTag("iconEnd").assertIsDisplayed()
    }

    @Test
    fun testSOHeader_iconClickDetected() {
        var iconClicked = false

        composeTestRule.setContent {
            SOHeader(
                headerText = "Test Header",
                iconEndRes = R.drawable.ic_download,
                onIconClicked = {
                    iconClicked = true
                }
            )
        }

        composeTestRule.onNodeWithTag("iconEnd").assertExists()
        composeTestRule.onNodeWithTag("iconEnd").assertIsDisplayed()

        assertEquals(false, iconClicked)
        composeTestRule.onNodeWithTag("iconEnd").performClick()
        assertEquals(true, iconClicked)
    }

}