package com.mikepenz.storyblok.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel

@Composable
fun StoryDetailsScreen(storyName: String, popBack: () -> Unit) {
    val storyblokViewModel = getViewModel<StoryblokViewModel>()

    Scaffold(
        topBar = {
            InsetAwareTopAppBar(
                title = { Text(storyName) },
                navigationIcon = {
                    IconButton(onClick = { popBack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val story = storyblokViewModel.getStory(storyName)
            story?.let {
                Text(story.name ?: "", style = MaterialTheme.typography.h4)
                Divider(Modifier.padding(12.dp))
                val bio = story.slug ?: ""
                Text(bio, style = MaterialTheme.typography.body1)
            }
        }
    }
}
