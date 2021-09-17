package com.mikepenz.storyblok

import com.mikepenz.common.BuildKonfig
import com.mikepenz.common.di.initKoin
import com.mikepenz.storyblok.sdk.Storyblok
import io.ktor.client.*
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class StoryblokTest {
    @Test
    fun testGetStories() = runBlocking {
        val koin = initKoin(enableNetworkLogs = true).koin
        val httpClient by koin.inject<HttpClient>()
        val result = Storyblok(BuildKonfig.STORYBLOK_TOKEN, httpClient).fetchStories()
        println(result)
        assertTrue(result.isNotEmpty())
    }
}
