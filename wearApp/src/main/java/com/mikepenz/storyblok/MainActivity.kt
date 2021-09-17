package com.mikepenz.storyblok

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.mikepenz.common.repository.StoryblokRepositoryInterface
import com.mikepenz.storyblok.sdk.model.Story
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val storyblokRepository: StoryblokRepositoryInterface by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                StoryList(storyblokRepository) {

                }
            }
        }

    }
}

@Composable
fun StoryList(storyblokRepository: StoryblokRepositoryInterface, storySelected: (story: Story) -> Unit) {
    var storyState by remember { mutableStateOf(emptyList<Story>()) }

    LaunchedEffect(true) {
        storyState = storyblokRepository.fetchStories()
    }

    Column {
        Text("Storyblok Sample", style = MaterialTheme.typography.title1)
        Spacer(modifier = Modifier.size(12.dp))

        LazyColumn {
            items(storyState) { story ->
                StoryView(story, storySelected)
            }
        }
    }
}


@Composable
fun StoryView(story: Story, storySelected: (story: Story) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { storySelected(story) })
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(50.dp))
        Spacer(modifier = Modifier.size(12.dp))

        Column {
            Text(text = story.name ?: "", style = TextStyle(fontSize = 15.sp))
            Text(text = story.fullSlug ?: "", style = TextStyle(color = Color.Gray, fontSize = 13.sp))
        }
    }
}
