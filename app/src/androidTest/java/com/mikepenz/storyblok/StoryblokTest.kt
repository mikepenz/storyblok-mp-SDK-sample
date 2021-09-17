package com.mikepenz.storyblok

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.mikepenz.storyblok.ui.StoryListScreen
import com.mikepenz.storyblok.ui.StoryListTag
import com.mikepenz.storyblok.ui.StoryblokViewModel
import org.junit.Rule
import org.junit.Test

class StoryblokTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val storyblokRepository = StoryblokRepositoryFake()
    private val storyblokViewModel = StoryblokViewModel(storyblokRepository)

    @Test
    fun testStoryListScreen() {
        composeTestRule.setContent {
            StoryListScreen(
                storySelected = {},
                storyblokViewModel = storyblokViewModel
            )
        }

        val storyList = storyblokRepository.storyList
        val storyListNode = composeTestRule.onNodeWithTag(StoryListTag)
        storyListNode.assertIsDisplayed()
            .onChildren().assertCountEquals(storyList.size)

        storyList.forEachIndexed { index, story ->
            val rowNode = storyListNode.onChildAt(index)
            rowNode.assertTextContains(story.name ?: "")
        }
    }
}
