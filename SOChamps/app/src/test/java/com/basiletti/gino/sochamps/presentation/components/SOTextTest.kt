package com.basiletti.gino.sochamps.presentation.components

import androidx.compose.ui.test.assertIsDisplayed
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Test

@RunWith(RobolectricTestRunner::class)
class SOTextTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSOTitle_contentDisplayed() {
        composeTestRule.setContent {
            SOTitle(text = "Test Title")
        }

        composeTestRule.onNodeWithText("Test Title").assertExists()
        composeTestRule.onNodeWithText("Test Title").assertIsDisplayed()
    }

    @Test
    fun testSOSubtitle_contentDisplayed() {
        composeTestRule.setContent {
            SOSubtitle(text = "Test Subtitle")
        }

        composeTestRule.onNodeWithText("Test Subtitle").assertExists()
        composeTestRule.onNodeWithText("Test Subtitle").assertIsDisplayed()
    }

    @Test
    fun testSODescription_contentDisplayed() {
        composeTestRule.setContent {
            SODescription(text = "Test Description")
        }

        composeTestRule.onNodeWithText("Test Description").assertExists()
        composeTestRule.onNodeWithText("Test Description").assertIsDisplayed()
    }

}