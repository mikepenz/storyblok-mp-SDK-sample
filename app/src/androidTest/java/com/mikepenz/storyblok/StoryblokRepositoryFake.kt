package com.mikepenz.storyblok

import com.mikepenz.common.repository.StoryblokRepositoryInterface
import com.mikepenz.storyblok.sdk.model.Story

class StoryblokRepositoryFake : StoryblokRepositoryInterface {
    val storyList = listOf(Story().apply {
        name = "test"
    })

    override suspend fun fetchStories(): List<Story> {
        return storyList
    }
}
