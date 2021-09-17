package com.mikepenz.storyblok.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.statusBarsPadding
import com.mikepenz.storyblok.sdk.model.Story
import org.koin.androidx.compose.getViewModel

const val StoryListTag = "StoryList"

@Composable
fun StoryListScreen(
    paddingValues: PaddingValues = PaddingValues(),
    storySelected: (story: Story) -> Unit,
    storyblokViewModel: StoryblokViewModel = getViewModel()
) {
    val storyState = storyblokViewModel.stories

    Scaffold(
        topBar = {
            InsetAwareTopAppBar(title = { Text("Storyblok Sample") })
        }
    ) {
        LazyColumn(contentPadding = paddingValues, modifier = Modifier.testTag(StoryListTag)) {
            items(storyState.value) { story ->
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
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(12.dp))

        Column {
            Text(text = story.name ?: "", style = TextStyle(fontSize = 20.sp))
            Text(text = story.slug ?: "", style = TextStyle(color = Color.DarkGray, fontSize = 14.sp))
        }
    }
}


/**
 * A wrapper around [TopAppBar] which uses [Modifier.statusBarsPadding] to shift the app bar's
 * contents down, but still draws the background behind the status bar too.
 */
@Composable
internal fun InsetAwareTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = 4.dp
) {
    Surface(
        color = backgroundColor,
        elevation = elevation,
        modifier = modifier
    ) {
        TopAppBar(
            title = title,
            navigationIcon = navigationIcon,
            actions = actions,
            backgroundColor = Color.Transparent,
            contentColor = contentColor,
            elevation = 0.dp,
            modifier = Modifier.statusBarsPadding()
        )
    }
}