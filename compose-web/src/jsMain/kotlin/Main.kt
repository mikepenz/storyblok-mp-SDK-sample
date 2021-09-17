import androidx.compose.runtime.*
import com.mikepenz.common.di.initKoin
import com.mikepenz.common.repository.StoryblokRepositoryInterface
import com.mikepenz.storyblok.sdk.model.Story
import kotlinx.coroutines.InternalCoroutinesApi
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable

private val koin = initKoin(enableNetworkLogs = true).koin

@InternalCoroutinesApi
fun main() {
    val repo = koin.get<StoryblokRepositoryInterface>()

    renderComposable(rootElementId = "root") {
        Style(TextStyles)

        var stories by remember { mutableStateOf(emptyList<Story>()) }

        LaunchedEffect(true) {
            stories = repo.fetchStories()
        }

        Div(attrs = { style { padding(16.px) } }) {
            H1(attrs = { classes(TextStyles.titleText) }) {
                Text("Storyblok Sample")
            }

            stories.forEach { story ->
                Div(
                    attrs = {
                        style {
                            display(DisplayStyle.Flex)
                            alignItems(AlignItems.Center)
                        }
                    }
                ) {
                    Span(attrs = { classes(TextStyles.storyText) }) {
                        Text("${story.name} (${story.slug})")
                    }
                }
            }
        }
    }
}

object TextStyles : StyleSheet() {

    val titleText by style {
        color(rgb(23, 24, 28))
        fontSize(50.px)
        property("font-size", 50.px)
        property("letter-spacing", (-1.5).px)
        property("font-weight", 900)
        property("line-height", 58.px)

        property(
            "font-family",
            "Gotham SSm A,Gotham SSm B,system-ui,-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Cantarell,Droid Sans,Helvetica Neue,Arial,sans-serif"
        )
    }

    val storyText by style {
        color(rgb(23, 24, 28))
        fontSize(24.px)
        property("font-size", 28.px)
        property("letter-spacing", "normal")
        property("font-weight", 300)
        property("line-height", 40.px)

        property(
            "font-family",
            "Gotham SSm A,Gotham SSm B,system-ui,-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Cantarell,Droid Sans,Helvetica Neue,Arial,sans-serif"
        )
    }
}
