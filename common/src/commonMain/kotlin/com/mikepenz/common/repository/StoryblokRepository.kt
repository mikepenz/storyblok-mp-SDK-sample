package com.mikepenz.common.repository

import com.mikepenz.common.BuildKonfig
import com.mikepenz.storyblok.sdk.Storyblok
import com.mikepenz.storyblok.sdk.model.Story
import io.ktor.client.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


interface StoryblokRepositoryInterface {
    suspend fun fetchStories(): List<Story>
}

class StoryblokRepository : KoinComponent, StoryblokRepositoryInterface {
    private val client: HttpClient by inject()

    private val api = Storyblok(BuildKonfig.STORYBLOK_TOKEN, client)

    // Used by web client atm
    override suspend fun fetchStories() = api.fetchStories()
}