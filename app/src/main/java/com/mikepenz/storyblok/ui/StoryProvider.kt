package com.mikepenz.storyblok.ui

import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import com.mikepenz.storyblok.sdk.model.Story

class StoryProvider : CollectionPreviewParameterProvider<Story>(
    listOf(
        Story().apply { name = "Test" },
        Story().apply { name = "Test 2" }
    )
)