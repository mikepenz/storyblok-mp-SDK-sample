import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import co.touchlab.kermit.Kermit
import com.mikepenz.common.di.initKoin
import com.mikepenz.common.repository.StoryblokRepositoryInterface
import com.mikepenz.storyblok.sdk.model.Story

private val koin = initKoin(enableNetworkLogs = true).koin

val logger = koin.get<Kermit>()

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        val repo = koin.get<StoryblokRepositoryInterface>()
        var storyState by remember { mutableStateOf(emptyList<Story>()) }
        var selectedStory by remember { mutableStateOf<Story?>(null) }

        LaunchedEffect(true) {
            storyState = repo.fetchStories()
            selectedStory = storyState.first()
        }

        StoryblokTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Storyblok Sample") },
                    )
                }
            ) {
                Row(Modifier.fillMaxSize()) {
                    Box(Modifier.width(250.dp).fillMaxHeight().background(MaterialTheme.colors.primaryVariant)) {
                        StoryList(storyState, selectedStory) {
                            selectedStory = it
                            logger.e { "${it.name}" }
                        }
                    }

                    Divider(modifier = Modifier.width(1.dp).fillMaxHeight())

                    Box(Modifier.fillMaxHeight()) {
                        selectedStory?.let {
                            StoryDetailsView(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StoryList(
    stories: List<Story>,
    selectedStory: Story?,
    storySelected: (story: Story) -> Unit
) {
    LazyColumn {
        items(stories) { story ->
            StoryView(story, selectedStory, storySelected)
        }
    }
}

@Composable
fun StoryView(
    story: Story,
    selectedStory: Story?,
    storySelected: (story: Story) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable(onClick = {
            storySelected(story)
        }).padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                story.name ?: "",
                style = if (story.name == selectedStory?.name) MaterialTheme.typography.h6 else MaterialTheme.typography.body1
            )
            Text(text = story.fullSlug ?: "", style = MaterialTheme.typography.caption)
        }
    }
}

@Composable
fun StoryDetailsView(story: Story) {
    LazyColumn(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item(story) {
            Text(story.name ?: "", style = MaterialTheme.typography.h4)
            Divider(modifier = Modifier.padding(12.dp))
            val bio = story.groupId ?: ""
            Text(bio, style = MaterialTheme.typography.body1)
        }
    }
}