package com.artlvr.animalfarm.poetry

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.GregorianCalendar

@RunWith(AndroidJUnit4::class)
class PoetryPageTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockPoetry =
        Poetry(
            name = "Animal Farm",
            sections =
                listOf(
                    Poetry.Section(
                        title = "Prefix",
                        content = "Hello",
                        date = GregorianCalendar(2022, 2, 15).time,
                    ),
                    Poetry.Section(
                        title = "Section 1",
                        content = "Android Project Ready to Go 🚀",
                        date = GregorianCalendar(2022, 2, 15).time,
                    ),
                ),
        )

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.artlvr.animalfarm", appContext.packageName)
    }

    @Test
    fun poetrySection_titleDisplayed() {
        setPoetry()
        composeTestRule
            .onNodeWithText("Prefix")
            .assertIsDisplayed()
    }

    @Test
    fun poetrySection_contentDisplayed() {
        setPoetry()
        composeTestRule
            .onNodeWithContentDescription("Hello")
            .assertIsDisplayed()
    }

    @Test
    fun poetrySection_pageScrolled() {
        setPoetry()
        composeTestRule
            .onNodeWithContentDescription("pager")
            .performScrollToIndex(1)
        composeTestRule
            .onNodeWithText("Section 1")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Prefix")
            .assertIsNotDisplayed()
        composeTestRule
            .onNodeWithText("Prefix")
            .assertExists()
    }

    private fun setPoetry() {
        composeTestRule.setContent {
            PoetryPage(poetry = mockPoetry)
        }
    }
}
