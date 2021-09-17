package com.mikepenz

import com.mikepenz.common.BuildKonfig
import com.mikepenz.common.di.initKoin
import com.mikepenz.storyblok.sdk.Storyblok
import io.ktor.client.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val koin = initKoin(enableNetworkLogs = true).koin
        val httpClient by koin.inject<HttpClient>()
        val result = Storyblok(BuildKonfig.STORYBLOK_TOKEN, httpClient).fetchStories()
        println(result)
    }
}
