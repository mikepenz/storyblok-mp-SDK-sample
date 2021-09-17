import com.mikepenz.storyblok.sdk.model.Story
import components.StoryDetails
import components.StoryList
import components.Typography
import components.materialui.AppBar
import components.materialui.Card
import components.materialui.Grid
import components.materialui.Toolbar
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.css.margin
import kotlinx.css.padding
import kotlinx.css.px
import react.*
import styled.css


@InternalCoroutinesApi
val App = functionalComponent<RProps> {
    val appDependencies = useContext(AppDependenciesContext)
    val repository = appDependencies.repository

    val (story, setStory) = useState(emptyList<Story>())
    val (selectedStory, setSelectedStory) = useState<Story?>(null)

    useEffectWithCleanup(dependencies = listOf()) {
        val mainScope = MainScope()

        mainScope.launch {
            val stories = repository.fetchStories()
            setStory(stories)
            setSelectedStory(stories.first())
        }
        return@useEffectWithCleanup { mainScope.cancel() }
    }
    Fragment {
        AppBar {
            css {
                margin(0.px)
            }
            Toolbar {
                Typography("h6", "Storyblok Sample")
            }
        }

        Toolbar {
            // Empty toolbar to avoid below content to be overlapped by AppBar
        }

        Grid {
            attrs {
                container = true
                spacing = 4
                justify = "flex-start"
                alignItems = "stretch"
            }

            Grid {
                attrs {
                    item = true
                    md = 4
                    xs = 12
                }
                StoryList(
                    story = story,
                    selectedStory = selectedStory,
                    onSelect = {
                        setSelectedStory(it)
                    }
                )
            }
            Grid {
                attrs {
                    item = true
                    md = 8
                    xs = 12
                }

                selectedStory?.let { story ->
                    Card {
                        css {
                            padding(16.px)
                        }

                        StoryDetails(story)
                    }
                }
            }
        }
    }
}