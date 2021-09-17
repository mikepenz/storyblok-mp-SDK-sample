package com.mikepenz.storyblok.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikepenz.common.repository.StoryblokRepositoryInterface
import com.mikepenz.storyblok.sdk.model.Story
import kotlinx.coroutines.launch

class StoryblokViewModel(
    private val storyblokRepository: StoryblokRepositoryInterface
) : ViewModel() {

    val stories = mutableStateOf(emptyList<Story>())

    init {
        viewModelScope.launch {
            stories.value = storyblokRepository.fetchStories()
        }
    }

    fun getStory(storyName: String): Story? {
        return stories.value.find { it.name == storyName }
    }
}
